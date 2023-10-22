SELECT
    curtidas.id AS id_curtida,
    candidatos.nome AS nome_candidato,
    empresas.nome AS nome_empresa,
    vagas.nome AS nome_vaga
FROM
    curtidas
        LEFT JOIN candidatos ON curtidas.id_candidatos = candidatos.id
        LEFT JOIN empresas ON curtidas.id_empresas = empresas.id
        LEFT JOIN vagas ON curtidas.id_vagas = vagas.id;