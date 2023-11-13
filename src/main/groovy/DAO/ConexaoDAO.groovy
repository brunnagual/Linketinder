package DAO


import java.sql.Connection
import java.sql.DriverManager

class ConexaoDAO {
    private String url
    private String usuario
    private String senha
    private Connection con

    private static ConexaoDAO instance = new ConexaoDAO()

    private ConexaoDAO() {
        url = "jdbc:postgresql://localhost:5432/postgres"
        usuario = "postgres"
        senha = "postgres"

        try {
            Class.forName("org.postgresql.Driver")
            con = DriverManager.getConnection(url, usuario, senha)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    static ConexaoDAO getInstance() {
        return instance
    }

    static void desconectar(Connection con) {
        if (con != null) {
            try {
                if (!con.isClosed()) {
                    con.close()
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
