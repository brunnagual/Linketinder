package DAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.ArrayList

class DatabaseUtil {

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
}