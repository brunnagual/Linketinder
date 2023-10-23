package DAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

import Users.Candidato

class CandidatoDB {

    static void listarCandidatos(Connection con) {
        String sql = "SELECT * FROM candidatos"
        ResultSet conjuntoResultados = null
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            conjuntoResultados = stmt.executeQuery()

            while (conjuntoResultados.next()) {
                exibirInformacoesCandidato(conjuntoResultados)
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            fecharConjuntoResultados(conjuntoResultados)
        }
    }

    static void fecharConjuntoResultados(ResultSet conjuntoResultados) {
        if (conjuntoResultados != null) {
            try {
                conjuntoResultados.close()
            } catch (SQLException e) {
                e.printStackTrace()
            }
        }
    }

    static void cadastrarCandidato(Candidato candidato, Connection con, Scanner scanner) {
        String nome = candidato.nome
        String sobrenome = candidato.sobrenome
        String email = candidato.email
        String cep = candidato.cep
        long cpf = candidato.cpf
        String descricaoPessoal = candidato.descricaoPessoal

        int candidatoId = inserirCandidatoNoBanco(con, nome, sobrenome, email, cep, cpf, descricaoPessoal)

        if (candidatoId != -1) {
            listarTodasCompetencias(con)

            while (associarCompetencia(candidatoId, con, scanner)) {
            }

            println("Candidato cadastrado com sucesso.")
        } else {
            println("Falha ao cadastrar o candidato.")
        }
    }

    static void exibirInformacoesCandidato(ResultSet conjuntoResultados) {
        String nome = conjuntoResultados.getString("nome")
        String sobrenome = conjuntoResultados.getString("sobrenome")
        String email = conjuntoResultados.getString("email")
        String cep = conjuntoResultados.getString("cep")
        String descricaoPessoal = conjuntoResultados.getString("descricao")

        println("$nome | $sobrenome | $email | $cep | $descricaoPessoal")
    }

    static int inserirCandidatoNoBanco(Connection con, String nome, String sobrenome, String email, String cep, long cpf, String descricaoPessoal) {
        String sqlInserirCandidato = "INSERT INTO public.candidatos(nome, sobrenome, email, cep, cpf, pais, descricao, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"

        try {
            PreparedStatement stmtCandidato = con.prepareStatement(sqlInserirCandidato, Statement.RETURN_GENERATED_KEYS)
            stmtCandidato.setString(1, nome)
            stmtCandidato.setString(2, sobrenome)
            stmtCandidato.setString(3, email)
            stmtCandidato.setString(4, cep)
            stmtCandidato.setLong(5, cpf)
            stmtCandidato.setString(6, "Brasil")
            stmtCandidato.setString(7, descricaoPessoal)
            stmtCandidato.setString(8, "senha_padrao")

            int rowsAffected = stmtCandidato.executeUpdate()
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmtCandidato.getGeneratedKeys()
                int candidatoId = -1

                if (generatedKeys.next()) {
                    candidatoId = generatedKeys.getInt(1)
                }

                return candidatoId
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
        return -1
    }

    static void listarTodasCompetencias(Connection con) {
        String sql = "SELECT * FROM competencias"
        ResultSet res = null

        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            res = stmt.executeQuery()

            println("Competências disponíveis:")
            while (res.next()) {
                int idCompetencia = res.getInt("id")
                String nomeCompetencia = res.getString("nome")
                println("$idCompetencia: $nomeCompetencia")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

    static boolean associarCompetencia(int candidatoId, Connection con, Scanner scanner) {
        Integer idCompetencia = capturarEntrada("Digite o número da competência que deseja associar: ", scanner)

        String sqlVerificarCompetencia = "SELECT * FROM competencias WHERE id = ?"
        try {
            PreparedStatement stmtVerificarCompetencia = con.prepareStatement(sqlVerificarCompetencia)
            stmtVerificarCompetencia.setInt(1, idCompetencia)
            ResultSet resultado = stmtVerificarCompetencia.executeQuery()

            if (resultado.next()) {

                String sqlAssociacao = "INSERT INTO public.candidatos_competencias(id_candidatos, id_competencias) VALUES (?, ?);"
                PreparedStatement stmtAssociacao = con.prepareStatement(sqlAssociacao)
                stmtAssociacao.setInt(1, candidatoId)
                stmtAssociacao.setInt(2, idCompetencia)
                stmtAssociacao.executeUpdate()

                println("Competência associada com sucesso.")
            } else {
                println("Competência não encontrada. Tente novamente.")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }

        println("Deseja associar outra competência? (S/N): ")
        String resposta = scanner.nextLine().toUpperCase()
        return resposta == "S"
    }

    static String capturarEntrada(String mensagem, Scanner scanner) {
        print(mensagem)
        return scanner.nextLine()
    }
}
