package DAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException

import static DAO.CandidatoDB.capturarEntrada

class competenciasDB {

    static void cadastrarCompetencia(Connection con, Scanner scanner) {
        def nomeCompetencia = capturarEntrada("Nome da competência: ", scanner)

        // Instrução SQL para inserir a competência
        String sqlCompetencia = "INSERT INTO competencias (nome) VALUES (?);"

        try {
            PreparedStatement stmtCompetencia = con.prepareStatement(sqlCompetencia)
            stmtCompetencia.setString(1, nomeCompetencia)

            int rowsAffected = stmtCompetencia.executeUpdate()
            if (rowsAffected > 0) {
                println("Competência cadastrada com sucesso.")
            } else {
                println("Falha ao cadastrar a competência.")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }
    }

}
