package DAO

import Controller.CandidatoController
import Controller.RegexController
import View.CandidatoView

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

import Model.CandidatoModel

class CandidatoDAO {
    private static final String SQL_INSERIR_CANDIDATO =
            "INSERT INTO public.candidatos(nome, sobrenome, email, cep, cpf, pais, descricao, senha) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);"

    private static final String SQL_SELECT_ALL_CANDIDATOS =
            "SELECT * FROM candidatos"

    private static final String SQL_SELECT_COMPETENCIA =
            "SELECT * FROM competencias WHERE id = ?"

    private static final String SQL_INSERT_CANDIDATO_COMPETENCIA =
            "INSERT INTO public.candidatos_competencias(id_candidatos, id_competencias) VALUES (?, ?);"

    static List<CandidatoModel> listarCandidatos() {

        // mudar logica vai trazer a lista do banco.
        Connection con = new ConexaoDAO().getConnection()
        ResultSet conjuntoResultados = null
        try {
            PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ALL_CANDIDATOS)
            conjuntoResultados = stmt.executeQuery()

            while (conjuntoResultados.next()) {
                CandidatoView.exibirInformacoesCandidato(conjuntoResultados)
            }
        } catch (SQLException e) {
            DatabaseUtilDAO.handleSQLException(e)
        } finally {
            fecharConjuntoResultados(conjuntoResultados)
        }
    }

    static int inserirCandidatoNoBanco(String nome, String sobrenome, String email, String cep, long cpf,
                                       String descricaoPessoal) {

        Connection con = new ConexaoDAO().getConnection()
        try {
            PreparedStatement stmtCandidato = con.prepareStatement(SQL_INSERIR_CANDIDATO, Statement.RETURN_GENERATED_KEYS)
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
        } catch (SQLException e) {
            DatabaseUtilDAO.handleSQLException(e)
        }
        return -1
    }

    static void fecharConjuntoResultados(ResultSet conjuntoResultados) {
        if (conjuntoResultados != null) {
            try {
                conjuntoResultados.close()
            } catch (SQLException e) {
                DatabaseUtilDAO.handleSQLException(e)
            }
        }
    }

    static void listarTodasCompetencias(Connection con) {
        try {
            DatabaseUtilDAO.listarTodasCompetencias(con)
        } finally {

        }
    }

    static boolean associarCompetencia(Connection con, int candidatoId, Scanner scanner) {
        int idCompetencia = RegexController.capturarEntradaInt("Digite o número da competência " +
                "que deseja associar: ", scanner)

        try {
            PreparedStatement stmtVerificarCompetencia = con.prepareStatement(SQL_SELECT_COMPETENCIA)
            stmtVerificarCompetencia.setInt(1, idCompetencia)
            ResultSet resultado = stmtVerificarCompetencia.executeQuery()

            if (resultado.next()) {
                String sqlAssociacao = SQL_INSERT_CANDIDATO_COMPETENCIA
                PreparedStatement stmtAssociacao = con.prepareStatement(sqlAssociacao)
                stmtAssociacao.setInt(1, candidatoId)
                stmtAssociacao.setInt(2, idCompetencia)
                stmtAssociacao.executeUpdate()
                println("Competência cadastrada com sucesso")
            } else {
                println("Competência não encontrada. Tente novamente.")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }

        println("Deseja associar outra competência? (S/N): ")
        String resposta = scanner.next().toUpperCase()
        return resposta.equals("S")
    }
}
