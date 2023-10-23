package DAO

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class Conexao {
    String url
    String usuario
    String senha
    Connection con

    Conexao(){
        url = "jdbc:postgresql://localhost:5432/postgres"
        usuario = "postgres"
        senha = "postgres"

        try {
            Class.forName("org.postgresql.Driver")
            con = DriverManager.getConnection(url,usuario,senha)
            println("------ Conexão com o Banco Realizada com sucesso !!! ------")
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
    ResultSet executaSQL(String sql){
        try{
            Statement stm = con.createStatement()
            ResultSet res = stm.executeUpdate(sql)
            con.close()
            return res
        }catch (Exception e){
            e.printStackTrace()
            return null
        }
    }
    static void desconectar(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close()
                println("------ Desconexão do Banco Realizada com sucesso !!! ------")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    Connection getConnection() {
        return con
    }
}
