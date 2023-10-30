package View

import java.sql.ResultSet
import java.sql.SQLException

class EmpresaView {
    static void exibirEmpresas(ResultSet conjuntoResultados) {
        try {
            while (conjuntoResultados.next()) {
                String nome = conjuntoResultados.getString("nome")
                String email = conjuntoResultados.getString("email")
                String cep = conjuntoResultados.getString("cep")
                String descricao = conjuntoResultados.getString("descricao")

                println("$nome | $email | $cep | $descricao")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }

    }
}
