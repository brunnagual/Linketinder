package DAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class DatabaseUtilDAO {

    static void listarTodasCompetencias() {
        Connection con = ConexaoDAO.getInstance().getConnection()
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

    static void handleSQLException(SQLException e) {
        e.printStackTrace()
    }
}