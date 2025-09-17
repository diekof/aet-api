package com.aet.api.repositories;

import com.aet.api.dto.ProjetoEixosDetalheView;
import com.aet.api.dto.ProjetoResumoEixosDTO;
import com.aet.api.dto.Unidade1DTO;
import com.aet.api.dto.Unidade2DTO;
import com.aet.api.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoCustomRepository extends JpaRepository<Projeto, Long> {

    @Query(value = """
        SELECT
          'Unidade 1' as projetoParte,
          ROW_NUMBER() OVER (PARTITION BY p.projeto_id ORDER BY p.projeto_id) AS numeroLinha,
          p.projeto_id AS projetoId,
          v.veiculo_placa as placa,
          v.veiculo_ano_fabricacao as anoFab,
          v.veiculo_chassi as chassi,
          mv.modelo_veiculo_marca as marca,
          mv.modelo_veiculo_nome as modelo,
          v.veiculo_peso as tara,
          v.veiculo_tracao as tracao,
          v.veiculo_potencia as potencia,
          v.veiculo_cmt as cmt,
          CASE WHEN v.veiculo_direcao = 1 THEN 'HIDRAULICA' ELSE 'MECANICA' END AS direcao,
          v.veiculo_renavam as renavam,
          v.veiculo_doc_numero as rntrc,
          CASE WHEN v.veiculo_bidirecional = 0 THEN 'Não' ELSE 'Sim' END AS bidirecional,
          p.projeto_comprimento as comprimento,
          p.projeto_PBTC_total as projetoPBTC,
          cv.combinacao_veicular_desc as combinacao
        FROM aet04.projeto p
        INNER JOIN aet04.ordem_servico_servico oss ON oss.ordem_servico_servico_id = p.ordem_servico_servico_id
        INNER JOIN aet04.veiculo v ON v.veiculo_id = oss.veiculo_id
        INNER JOIN aet04.modelo_veiculo mv ON mv.modelo_veiculo_id = v.modelo_veiculo_id
        INNER JOIN aet04.combinacao_veicular cv ON cv.combinacao_veicular_id = p.combinacao_veicular_id
        WHERE p.projeto_id = :projetoId
        """, nativeQuery = true)
    List<Unidade1DTO> findUnidade1(Long projetoId);

    @Query(value = """
        SELECT
          'Unidade 2' as projetoParte,
          ROW_NUMBER() OVER (PARTITION BY p.projeto_id ORDER BY c.carreta_id) + 1 AS numeroLinha,
          p.projeto_id AS projetoId,
          c.carreta_placa as placa,
          c.carreta_ano_fab  as anoFab,
          c.carreta_chassi as chassi,
          mv.modelo_veiculo_marca as marca,
          mv.modelo_veiculo_nome as modelo,
          c.carreta_peso_tara as tara,
          c.carreta_renavam as renavam,
          c.carreta_antt as rntrc,
          c.carreta_qtd_eixo as rodaPorEixo,
          c.carreta_num_eixos as numeroEixo
        FROM aet04.projeto p
        INNER JOIN aet04.projetocarretas_principais pp ON pp.projeto_id  = p.projeto_id
        INNER JOIN aet04.carreta c ON c.carreta_id = pp.carreta_id
        INNER JOIN aet04.modelo_veiculo mv ON mv.modelo_veiculo_id = c.carreta_modelo_id
        INNER JOIN aet04.tipo_carroceria tc ON tc.tipo_carroceria_id = c.tipo_carroceria_id
        WHERE p.projeto_id = :projetoId
        """, nativeQuery = true)
    List<Unidade2DTO> findUnidade2(Long projetoId);

    @Query(value = """
        SELECT
          'Carretas Rodizio' as projetoParte,
          ROW_NUMBER() OVER (PARTITION BY p.projeto_id ORDER BY c.carreta_id) AS numeroLinha,
          p.projeto_id AS projetoId,
          c.carreta_placa as placa,
          c.carreta_ano_fab  as anoFab,
          c.carreta_chassi as chassi,
          mv.modelo_veiculo_marca as marca,
          mv.modelo_veiculo_nome as modelo,
          c.carreta_peso_tara as tara,
          c.carreta_renavam as renavam,
          c.carreta_antt as rntrc,
          c.carreta_qtd_eixo as rodaPorEixo,
          c.carreta_num_eixos as numeroEixo
        FROM aet04.projeto p
        INNER JOIN aet04.projetocarretas_rodizio  pp ON pp.projeto_id  = p.projeto_id
        INNER JOIN aet04.carreta c ON c.carreta_id = pp.proj_carreta_rod_carreta_id
        INNER JOIN aet04.modelo_veiculo mv ON mv.modelo_veiculo_id = c.carreta_modelo_id
        INNER JOIN aet04.tipo_carroceria tc ON tc.tipo_carroceria_id = c.tipo_carroceria_id
        WHERE p.projeto_id = :projetoId
        """, nativeQuery = true)
    List<Unidade2DTO> findRodizio(Long projetoId);

    @Query(value = """
            SELECT
                p.projeto_id                                      AS projetoId,
                SUM(TipoEixo_Peso)                                AS qtdTipoeixoPeso,
                COUNT(DISTINCT tn.TVE_Nivel_Nivel)                AS niveis,
                SUM(CASE WHEN tn.TVE_Nivel_Nivel = 1 AND tn1.TipoEixo_rodas > 0 THEN 1 ELSE 0 END) AS qtdNivel1,
                SUM(CASE WHEN tn.TVE_Nivel_Nivel = 2 AND tn1.TipoEixo_rodas > 0 THEN 1 ELSE 0 END) AS qtdNivel2,
                SUM(CASE WHEN tn.TVE_Nivel_Nivel = 3 AND tn1.TipoEixo_rodas > 0 THEN 1 ELSE 0 END) AS qtdNivel3,
                SUM(CASE WHEN tn.TVE_Nivel_Nivel = 4 AND tn1.TipoEixo_rodas > 0 THEN 1 ELSE 0 END) AS qtdNivel4,
            
                mv.modelo_veiculo_reducao_1ic   AS modeloVeiculoReducao1ic,
                mv.modelo_veiculo_reducao_2ic   AS modeloVeiculoReducao2ic,
                mv.modelo_veiculo_reducao_3ic   AS modeloVeiculoReducao3ic,
                mv.modelo_veiculo_reducao_4ic   AS modeloVeiculoReducao4ic,
                mv.modelo_veiculo_reducao_5ic   AS modeloVeiculoReducao5ic,
                mv.modelo_veiculo_reducao_6ic   AS modeloVeiculoReducao6ic,
                mv.modelo_veiculo_reducao_7ic   AS modeloVeiculoReducao7ic,
                mv.modelo_veiculo_reducao_8ic   AS modeloVeiculoReducao8ic,
                mv.modelo_veiculo_reducao_9ic   AS modeloVeiculoReducao9ic,
                mv.modelo_veiculo_reducao_10ic  AS modeloVeiculoReducao10ic,
                mv.modelo_veiculo_reducao_11ic  AS modeloVeiculoReducao11ic,
                mv.modelo_veiculo_reducao_12ic  AS modeloVeiculoReducao12ic,
                mv.modelo_veiculo_reducao_13ic  AS modeloVeiculoReducao13ic,
                mv.modelo_veiculo_reducao_14ic  AS modeloVeiculoReducao14ic,
                mv.modelo_veiculo_reducao_15ic  AS modeloVeiculoReducao15ic,
                mv.modelo_veiculo_reducao_16ic  AS modeloVeiculoReducao16ic,
                mv.modelo_veiculo_reducao_17ic  AS modeloVeiculoReducao17ic,
                mv.modelo_veiculo_reducao_18ic  AS modeloVeiculoReducao18ic,
                mv.modelo_veiculo_reducao_19ic  AS modeloVeiculoReducao19ic,
                mv.modelo_veiculo_reducao_20ic  AS modeloVeiculoReducao20ic,
            
                CASE
                    WHEN COALESCE(mv.modelo_veiculo_reducao_1ic,  0) = 0 THEN 0
                    WHEN COALESCE(mv.modelo_veiculo_reducao_2ic,  0) = 0 THEN 1
                    WHEN COALESCE(mv.modelo_veiculo_reducao_3ic,  0) = 0 THEN 2
                    WHEN COALESCE(mv.modelo_veiculo_reducao_4ic,  0) = 0 THEN 3
                    WHEN COALESCE(mv.modelo_veiculo_reducao_5ic,  0) = 0 THEN 4
                    WHEN COALESCE(mv.modelo_veiculo_reducao_6ic,  0) = 0 THEN 5
                    WHEN COALESCE(mv.modelo_veiculo_reducao_7ic,  0) = 0 THEN 6
                    WHEN COALESCE(mv.modelo_veiculo_reducao_8ic,  0) = 0 THEN 7
                    WHEN COALESCE(mv.modelo_veiculo_reducao_9ic,  0) = 0 THEN 8
                    WHEN COALESCE(mv.modelo_veiculo_reducao_10ic, 0) = 0 THEN 9
                    WHEN COALESCE(mv.modelo_veiculo_reducao_11ic, 0) = 0 THEN 10
                    WHEN COALESCE(mv.modelo_veiculo_reducao_12ic, 0) = 0 THEN 11
                    WHEN COALESCE(mv.modelo_veiculo_reducao_13ic, 0) = 0 THEN 12
                    WHEN COALESCE(mv.modelo_veiculo_reducao_14ic, 0) = 0 THEN 13
                    WHEN COALESCE(mv.modelo_veiculo_reducao_15ic, 0) = 0 THEN 14
                    WHEN COALESCE(mv.modelo_veiculo_reducao_16ic, 0) = 0 THEN 15
                    WHEN COALESCE(mv.modelo_veiculo_reducao_17ic, 0) = 0 THEN 16
                    WHEN COALESCE(mv.modelo_veiculo_reducao_18ic, 0) = 0 THEN 17
                    WHEN COALESCE(mv.modelo_veiculo_reducao_19ic, 0) = 0 THEN 18
                    WHEN COALESCE(mv.modelo_veiculo_reducao_20ic, 0) = 0 THEN 19
                    ELSE 0
                END AS qtdMarchaa,
            
                mv.modelo_veiculo_coef_atrito AS coeficienteAtrito,
                mv.modelo_veiculo_raio_dinam  AS raio,
                mv.modelo_veiculo_resist_rolam AS resistenciaRolamento,
                mv.modelo_veiculo_tipo_pneu   AS tipoPneu,
                mv.modelo_veiculo_torque_max  AS torque,
                mv.modelo_veiculo_reducao_max AS reducaoMax,
                mv.modelo_veiculo_reducao_eixo AS reducaoEixoTras,
                mv.modelo_veiculo_potencia_max AS RPM
            
            FROM aet04.projeto p
            JOIN aet04.ordem_servico_servico oss ON oss.ordem_servico_servico_id = p.ordem_servico_servico_id
            JOIN aet04.veiculo v               ON oss.veiculo_id = v.veiculo_id
            JOIN aet04.modelo_veiculo mv       ON mv.modelo_veiculo_id = v.modelo_veiculo_id
            JOIN aet04.tipo_servico ts         ON ts.tipo_servico_id = oss.tipo_servico_id
            JOIN aet04.tipo_veiculo tv         ON tv.tipo_veiculo_id  = ts.tipo_veiculo_id
            JOIN aet04.tipo_veiculo_eixo tve   ON tve.tipo_veiculo_id = tv.tipo_veiculo_id
                                               AND tve.tipo_veiculoEixo_numeixos = p.projeto_num_eixos
                                               AND tve.modelo_veiculo_id = mv.modelo_veiculo_id
            JOIN aet04.tve_nivel tn            ON tn.tipo_veiculoEixo_id = tve.tipo_veiculoEixo_id
            JOIN aet04.tve_niveltipoeixo tn1   ON tn1.TVE_Nivel_id = tn.TVE_Nivel_id
            WHERE p.projeto_id = :projetoId
              AND tv.tipo_veiculo_ativo = 1
              AND (
                    SELECT SUM(a.TipoEixo_distancia)
                    FROM aet04.tve_nivel b
                    JOIN aet04.tve_niveltipoeixo a ON a.TVE_Nivel_id = b.TVE_Nivel_id
                    WHERE b.tipo_veiculoEixo_id = tve.tipo_veiculoEixo_id
                  ) = p.projeto_comprimento
""", nativeQuery = true)
    ProjetoResumoEixosDTO buscarResumoPorProjeto(@Param("projetoId") Long projetoId);


    @Query(value = """
SELECT 
    p.projeto_id AS projetoId,

    CASE tipo_distancia_entre_eixos
        WHEN 1  THEN 'A' WHEN 2  THEN 'B' WHEN 3  THEN 'C' WHEN 4  THEN 'D'
        WHEN 5  THEN 'E' WHEN 6  THEN 'F' WHEN 7  THEN 'G' WHEN 8  THEN 'H'
        WHEN 9  THEN 'I' WHEN 10 THEN 'J' WHEN 11 THEN 'K' WHEN 12 THEN 'L'
        WHEN 13 THEN 'M' WHEN 14 THEN 'N' WHEN 15 THEN 'O' WHEN 16 THEN 'P'
    END AS letra,

    tn1.TipoEixo_rodas      AS tipoEixoRodas,
    CASE tn1.TipoEixo_tandem WHEN 1 THEN 'Sim' ELSE 'Não' END AS tipoEixoTandem,
    tn1.TipoEixo_distancia  AS tipoEixoDistancia,
    tn1.TipoEixo_tipo       AS tipoEixoTipo,

    CASE tn1.TipoEixo_tipo
        WHEN 1 THEN 'Eixo Simples'
        WHEN 2 THEN 'Eixo Duplo'
        WHEN 3 THEN 'Eixo Triplo'
        WHEN 4 THEN 'Balanço traseiro'
        WHEN 5 THEN 'Eixo Dianteiro'
        ELSE 'Distância entre eixo'
    END AS eixo,

    TipoEixo_L              AS tipoEixoL,
    CASE WHEN TipoEixo_L > 10 THEN 2 ELSE 1 END AS fatorL,
    tn1.TipoEixo_Peso       AS tipoEixoPeso,
    tn.TVE_Nivel_Nivel      AS nivel

FROM aet04.projeto p 
JOIN aet04.ordem_servico_servico oss ON oss.ordem_servico_servico_id = p.ordem_servico_servico_id 
JOIN aet04.veiculo v               ON oss.veiculo_id = v.veiculo_id 
JOIN aet04.modelo_veiculo mv       ON mv.modelo_veiculo_id = v.modelo_veiculo_id 
JOIN aet04.tipo_servico ts         ON ts.tipo_servico_id = oss.tipo_servico_id  
JOIN aet04.tipo_veiculo tv         ON tv.tipo_veiculo_id  = ts.tipo_veiculo_id 
JOIN aet04.tipo_veiculo_eixo tve   ON tve.tipo_veiculo_id  = tv.tipo_veiculo_id 
                                   AND tve.tipo_veiculoEixo_numeixos = p.projeto_num_eixos 
                                   AND tve.modelo_veiculo_id = mv.modelo_veiculo_id
JOIN aet04.tve_nivel tn            ON tn.tipo_veiculoEixo_id = tve.tipo_veiculoEixo_id
JOIN aet04.tve_niveltipoeixo tn1   ON tn1.TVE_Nivel_id = tn.TVE_Nivel_id
WHERE p.projeto_id = :projetoId
  AND tv.tipo_veiculo_ativo = 1
  AND (
        SELECT SUM(a.TipoEixo_distancia)
        FROM aet04.tve_nivel b
        JOIN aet04.tve_niveltipoeixo a ON a.TVE_Nivel_id = b.TVE_Nivel_id 
        WHERE b.tipo_veiculoEixo_id = tve.tipo_veiculoEixo_id
      ) = p.projeto_comprimento
""", nativeQuery = true)
    List<ProjetoEixosDetalheView> buscarDetalhesPorProjeto(@Param("projetoId") Long projetoId);

}
