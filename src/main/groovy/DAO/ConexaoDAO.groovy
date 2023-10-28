package DAO

import java.sql.Connection
import java.sql.DriverManager

class ConexaoDAO {
    private String url
    private String usuario
    private String senha
    private Connection con

    ConexaoDAO() {
        url = "jdbc:postgresql://localhost:5432/postgres"
        usuario = "postgres"
        senha = "postgres"

        try {
            Class.forName("org.postgresql.Driver")
            con = DriverManager.getConnection(url, usuario, senha)
            println("------ Conexão com o Banco Realizada com sucesso !!! ------")
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    static void desconectar(Connection con) {
        if (con != null) {
            try {
                if (!con.isClosed()) {
                    con.close()
                    println("------ Desconexão do Banco Realizada com sucesso !!! ------")
                }
            } catch (Exception e) {
                e.printStackTrace()
            }
        }
    }
    Connection getConnection() {
        return con
    }
}
