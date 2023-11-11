package DAO

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
            "SELECT nome, sobrenome, email, cep, cpf, pais, descricao, senha  FROM candidatos"

    private static final String SQL_SELECT_COMPETENCIA =
            "SELECT * FROM competencias WHERE id = ?"

    private static final String SQL_INSERT_CANDIDATO_COMPETENCIA =
            "INSERT INTO public.candidatos_competencias(id_candidatos, id_competencias) VALUES (?, ?);"

    static List<CandidatoModel> listarCandidatos() {

        Connection con = ConexaoDAO.getInstance().getConnection()

        ResultSet conjuntoResultados = null
        try {
            PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ALL_CANDIDATOS)
            conjuntoResultados = stmt.executeQuery()

            List<CandidatoModel> ListaCandidatos = []
            while (conjuntoResultados.next()) {

                String nome = conjuntoResultados.getString(1)
                String sobrenome = conjuntoResultados.getString(2)
                String email = conjuntoResultados.getString(3)
                String cep = conjuntoResultados.getString(4)
                Long cpf = conjuntoResultados.getLong(5)
                String descricaoPessoal = conjuntoResultados.getString(7)

                CandidatoModel candidato = new CandidatoModel(nome,sobrenome,email,cep,cpf,descricaoPessoal,[])
                ListaCandidatos << candidato
            }
            return ListaCandidatos

        } catch (SQLException e) {
            DatabaseUtilDAO.handleSQLException(e)
        }
    }

    static int inserirCandidatoNoBanco(String nome, String sobrenome, String email, String cep, long cpf,
                                       String descricaoPessoal) {

        Connection con = ConexaoDAO.getInstance().getConnection()
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

    static void listarTodasCompetencias() {
        Connection con = ConexaoDAO.getInstance().getConnection()
        try {
            DatabaseUtilDAO.listarTodasCompetencias()
        } finally {

        }
    }

    static boolean associarCompetencia(int candidatoId, int idCompetencia) {
        Connection con = ConexaoDAO.getInstance().getConnection()

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
    }
}
