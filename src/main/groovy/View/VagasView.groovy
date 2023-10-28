package View

import DAO.ConexaoDAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class VagasView {

    static void exibirVagas(ResultSet res) {
        Connection con = new ConexaoDAO().getConnection()
        try {
            PreparedStatement stmt = con.prepareStatement()
            res = stmt.executeQuery()

            while (res.next()) {
                String id = res.getString("id")
                String nome = res.getString("nome")
                String descricao = res.getString("descricao")
                String salario = res.getString("salario")
                String nomeEmpresa = res.getString("empresa")

                println("$id | $nome | $descricao | $salario | $nomeEmpresa")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            if (res != null) {
                try {
                    res.close()
                } catch (SQLException e) {
                    e.printStackTrace()
                }
            }
        }
        ConexaoDAO.desconectar(con)
    }
}
