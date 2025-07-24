package com.aet.api.repositories;

import com.aet.api.dto.Unidade1DTO;
import com.aet.api.dto.Unidade2DTO;
import com.aet.api.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
          mv.modelo_veiculo_descricao as modelo,
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
          mv.modelo_veiculo_descricao as modelo,
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
          mv.modelo_veiculo_descricao as modelo,
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
}
