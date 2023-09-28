package BD

import java.sql.Connection
import java.sql.DriverManager

class conexao {
    String url
    String usuario
    String senha
    Connection con

    conexao(){
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
}
