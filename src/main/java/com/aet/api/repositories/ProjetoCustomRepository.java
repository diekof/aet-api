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
          'Unidade 1' as projeto_parte,
          ROW_NUMBER() OVER (PARTITION BY p.projeto_id ORDER BY p.projeto_id) AS numero_linha,
          p.projeto_id,
          v.veiculo_placa as placa,
          v.veiculo_ano_fabricacao as ano_fab,
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
        INNER JOIN aet04.Combinacao_veicular cv ON cv.combinacao_veicular_id = p.combinacao_veicular_id
        WHERE p.projeto_id = :projetoId
        """, nativeQuery = true)
    List<Unidade1DTO> findUnidade1(Long projetoId);

    @Query(value = """
        SELECT
          'Unidade 2' as projeto_parte,
          ROW_NUMBER() OVER (PARTITION BY p.projeto_id ORDER BY c.carreta_id) + 1 AS numero_linha,
          p.projeto_id,
          c.carreta_placa as placa,
          c.carreta_ano_fab  as ano_fab,
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
          'Carretas Rodizio' as projeto_parte,
          ROW_NUMBER() OVER (PARTITION BY p.projeto_id ORDER BY c.carreta_id) AS numero_linha,
          p.projeto_id,
          c.carreta_placa as placa,
          c.carreta_ano_fab  as ano_fab,
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
            SELECT p.projeto_id, sum(TipoEixo_Peso),
            count(distinct tn.TVE_Nivel_Nivel) as Niveis,
            sum(case when tn.TVE_Nivel_Nivel = 1 and tn1.TipoEixo_rodas > 0 then 1 else 0 end) as qtd_nivel_1,
            sum(case when tn.TVE_Nivel_Nivel = 2 and tn1.TipoEixo_rodas > 0 then 1 else 0 end) as qtd_nivel_2,
            sum(case when tn.TVE_Nivel_Nivel = 3 and tn1.TipoEixo_rodas > 0 then 1 else 0 end) as qtd_nivel_3,
            sum(case when tn.TVE_Nivel_Nivel = 4 and tn1.TipoEixo_rodas > 0 then 1 else 0 end) as qtd_nivel_4,
            mv.modelo_veiculo_reducao_1ic,
            mv.modelo_veiculo_reducao_2ic,
            mv.modelo_veiculo_reducao_3ic,
            mv.modelo_veiculo_reducao_4ic,
            mv.modelo_veiculo_reducao_5ic,
            mv.modelo_veiculo_reducao_6ic,
            mv.modelo_veiculo_reducao_7ic,
            mv.modelo_veiculo_reducao_8ic,
            mv.modelo_veiculo_reducao_9ic,
            mv.modelo_veiculo_reducao_10ic,
            mv.modelo_veiculo_reducao_11ic,
            mv.modelo_veiculo_reducao_12ic,
            mv.modelo_veiculo_reducao_13ic,
            mv.modelo_veiculo_reducao_14ic,
            mv.modelo_veiculo_reducao_15ic,
            mv.modelo_veiculo_reducao_16ic,
            mv.modelo_veiculo_reducao_17ic,
            mv.modelo_veiculo_reducao_18ic,
            mv.modelo_veiculo_reducao_19ic,
            mv.modelo_veiculo_reducao_20ic,
            case
            when coalesce(mv.modelo_veiculo_reducao_1ic,0) = 0 then 0
            when coalesce(mv.modelo_veiculo_reducao_2ic,0) = 0 then 1
            when coalesce(mv.modelo_veiculo_reducao_3ic,0) = 0 then 2
            when coalesce(mv.modelo_veiculo_reducao_4ic,0) = 0 then 3
            when coalesce(mv.modelo_veiculo_reducao_5ic,0) = 0 then 4
            when coalesce(mv.modelo_veiculo_reducao_6ic,0) = 0 then 5
            when coalesce(mv.modelo_veiculo_reducao_7ic,0) = 0 then 6
            when coalesce(mv.modelo_veiculo_reducao_8ic,0) = 0 then 7
            when coalesce(mv.modelo_veiculo_reducao_9ic,0) = 0 then 8
            when coalesce(mv.modelo_veiculo_reducao_10ic,0) = 0 then 9
            when coalesce(mv.modelo_veiculo_reducao_11ic,0) = 0 then 10
            when coalesce(mv.modelo_veiculo_reducao_12ic,0) = 0 then 11
            when coalesce(mv.modelo_veiculo_reducao_13ic,0) = 0 then 12
            when coalesce(mv.modelo_veiculo_reducao_14ic,0) = 0 then 13
            when coalesce(mv.modelo_veiculo_reducao_15ic,0) = 0 then 14
            when coalesce(mv.modelo_veiculo_reducao_16ic,0) = 0 then 15
            when coalesce(mv.modelo_veiculo_reducao_17ic,0) = 0 then 16
            when coalesce(mv.modelo_veiculo_reducao_18ic,0) = 0 then 17
            when coalesce(mv.modelo_veiculo_reducao_19ic,0) = 0 then 18
            when coalesce(mv.modelo_veiculo_reducao_20ic,0) = 0 then 19
            else 0 end as qtd_marchaa,
            mv.modelo_veiculo_coef_atrito as coeficiente_atrito,
            mv.modelo_veiculo_raio_dinam as raio,
            mv.modelo_veiculo_resist_rolam as resistencia_rolamento,
            mv.modelo_veiculo_tipo_pneu as tipo_pneu,
            mv.modelo_veiculo_torque_max as torque,
            mv.modelo_veiculo_reducao_max as reducao_max
            from aet04.projeto p
            inner join aet04.ordem_servico_servico oss on oss.ordem_servico_servico_id = p.ordem_servico_servico_id
            inner join aet04.veiculo v on oss.veiculo_id = v.veiculo_id
            inner join aet04.modelo_veiculo mv on mv.modelo_veiculo_id = v.modelo_veiculo_id
            inner join aet04.tipo_servico ts on ts.tipo_servico_id = oss.tipo_servico_id 
            inner join aet04.tipo_veiculo tv on tv.tipo_veiculo_id  = ts.tipo_veiculo_id
            inner join aet04.tipo_veiculo_eixo tve on tve.tipo_veiculo_id  = tv.tipo_veiculo_id
            and tve.tipo_veiculoEixo_numeixos = p.projeto_num_eixos
            and tve.modelo_veiculo_id = mv.modelo_veiculo_id
            inner join aet04.tve_nivel tn on tn.tipo_veiculoEixo_id = tve.tipo_veiculoEixo_id
            inner join aet04.tve_niveltipoeixo tn1 on tn1.TVE_Nivel_id = tn.TVE_Nivel_id
            where p.projeto_id = :projetoId
             and tv.tipo_veiculo_ativo = 1
            and (select sum(a.TipoEixo_distancia ) from aet04.tve_nivel b
            inner join aet04.tve_niveltipoeixo a on a.TVE_Nivel_id = b.TVE_Nivel_id
            	where b.tipo_veiculoEixo_id = tve.tipo_veiculoEixo_id) = p.projeto_comprimento            
            """, nativeQuery = true)
    ProjetoResumoEixosDTO buscarResumoPorProjeto(Long projetoId);

    @Query(value = """
            SELECT p.projeto_id,
            case tipo_distancia_entre_eixos
            when 1 then 'A'
            when 2 then 'B'
            when 3 then 'C'
            when 4 then 'D'
            when 5 then 'E'
            when 6 then 'F'
            when 7 then 'G'
            when 8 then 'H'
            when 9 then 'I'
            when 10 then 'J'
            when 11 then 'K'
            when 12 then 'L'
            when 13 then 'M'
            when 14 then 'N'
            when 15 then 'O'
            when 16 then 'P'
            end AS LETRA,
            tn1.TipoEixo_rodas ,
            case tn1.TipoEixo_tandem
            when 1 then 'Sim' else 'Não' end as TipoEixo_tandem,
            tn1.TipoEixo_distancia,
            tn1.TipoEixo_tipo ,
            case tn1.TipoEixo_tipo
            when 1 then 'Eixo Simples'
            when 2 then 'Eixo Duplo'
            when 3 then 'Eixo Triplo'
            when 4 then 'Balanço traseiro'
            when 5 then 'Eixo Dianteiro'
            else 'Distância entre eixo'
            end as eixo,
            TipoEixo_L,
            case when TipoEixo_L > 10 then 2 else 1 end as fator_L,
            tn1.TipoEixo_Peso ,
            tn.TVE_Nivel_Nivel nivel
            from aet04.projeto p
            inner join aet04.ordem_servico_servico oss on oss.ordem_servico_servico_id = p.ordem_servico_servico_id
            inner join aet04.veiculo v on oss.veiculo_id = v.veiculo_id
            inner join aet04.modelo_veiculo mv on mv.modelo_veiculo_id = v.modelo_veiculo_id
            inner join aet04.tipo_servico ts on ts.tipo_servico_id = oss.tipo_servico_id 
            inner join aet04.tipo_veiculo tv on tv.tipo_veiculo_id  = ts.tipo_veiculo_id
            inner join aet04.tipo_veiculo_eixo tve on tve.tipo_veiculo_id  = tv.tipo_veiculo_id
            and tve.tipo_veiculoEixo_numeixos = p.projeto_num_eixos
            and tve.modelo_veiculo_id = mv.modelo_veiculo_id
            inner join aet04.tve_nivel tn on tn.tipo_veiculoEixo_id = tve.tipo_veiculoEixo_id
            inner join aet04.tve_niveltipoeixo tn1 on tn1.TVE_Nivel_id = tn.TVE_Nivel_id
            where p.projeto_id = :projetoId
             and tv.tipo_veiculo_ativo = 1
            and (select sum(a.TipoEixo_distancia ) from aet04.tve_nivel b
            inner join aet04.tve_niveltipoeixo a on a.TVE_Nivel_id = b.TVE_Nivel_id
            	where b.tipo_veiculoEixo_id = tve.tipo_veiculoEixo_id) = p.projeto_comprimento            
            """, nativeQuery = true)
    List<ProjetoEixosDetalheView> buscarDetalhesPorProjeto(Long projetoId);

}
