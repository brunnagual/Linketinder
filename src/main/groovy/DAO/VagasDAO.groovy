package DAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import Model.VagasModel

import static Controller.RegexController.capturarEntrada

class VagasDAO {

    static void listarVagas() {
        Connection con = new ConexaoDAO().getConnection()

        String sql = "SELECT v.id, v.nome, v.descricao, v.salario, e.nome AS empresa FROM vagas AS v, empresas AS e WHERE v.id_empresa = e.id;"
        ResultSet res = null

        try {
            PreparedStatement stmt = con.prepareStatement(sql)
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

    static void cadastrarVaga(VagasModel vaga, Scanner scanner) {
        Connection con = new ConexaoDAO().getConnection()

        String nome = vaga.nome
        String descricao = vaga.descricao
        double salario = vaga.salario
        String nomeEmpresa = vaga.nomeEmpresa
        int idEmpresa = consultarIdEmpresa(nomeEmpresa)

        listarCompetenciasDisponiveis()

        int idCompetencia = capturarEntrada("Digite o número da competência que deseja associar (ou F para finalizar e cadastrar vaga): ", scanner) as int

        List<Integer> competenciasAssociadas = new ArrayList<Integer>()

        while (true) {
            String input = capturarEntrada("Digite o número da competência que deseja associar (ou F para finalizar e cadastrar a vaga): ", scanner).toUpperCase()
            if (input.equals("F")) {
                break
            }

            try {
                int idCompetenciaSelecionada = Integer.parseInt(input)
                if (verificarCompetenciaExistente(idCompetenciaSelecionada)) {
                    competenciasAssociadas.add(idCompetenciaSelecionada)
                    println("Competência associada com sucesso.")
                } else {
                    println("Competência não encontrada. Tente novamente.")
                }
            } catch (NumberFormatException e) {
                println("Entrada inválida. Digite um número válido ou 'F' para finalizar.")
            }
        }

        String InserirVaga = "INSERT INTO vagas (nome, descricao, salario, id_empresa, id_competencias) VALUES (?, ?, ?, ?, ?) RETURNING id"

        try {
            PreparedStatement stmtVaga = con.prepareStatement(InserirVaga, Statement.RETURN_GENERATED_KEYS)
            stmtVaga.setString(1, nome)
            stmtVaga.setString(2, descricao)
            stmtVaga.setDouble(3, salario)
            stmtVaga.setInt(4, idEmpresa)
            stmtVaga.setInt(5, idCompetencia)

            int rowsAffected = stmtVaga.executeUpdate()
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmtVaga.getGeneratedKeys()
                int vagaId = -1

                if (generatedKeys.next()) {
                    vagaId = generatedKeys.getInt(1)
                }
                for (int idComp : competenciasAssociadas) {
                    associarCompetencia(vagaId, idComp)
                }
                println("Vaga cadastrada com sucesso.")
            } else {
                println("Falha ao cadastrar a Vaga.")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
        ConexaoDAO.desconectar(con)
    }

    static void listarCompetenciasDisponiveis() {
        Connection con = new ConexaoDAO().getConnection()

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
        ConexaoDAO.desconectar(con)
    }

    static boolean verificarCompetenciaExistente(int idCompetencia) {
        Connection con = new ConexaoDAO().getConnection()

        String sqlVerificar = "SELECT id FROM competencias WHERE id = ?"
        try {
            PreparedStatement stmtVerificar = con.prepareStatement(sqlVerificar)
            stmtVerificar.setInt(1, idCompetencia)
            ResultSet resultado = stmtVerificar.executeQuery()

            return resultado.next()
        } catch (SQLException e) {
            e.printStackTrace()
        }
        ConexaoDAO.desconectar(con)
        return false
    }

    static void associarCompetencia(int vagaId, int idCompetencia) {
        Connection con = new ConexaoDAO().getConnection()

        String sqlAssociacao = "INSERT INTO vagas_competencias (id_vagas, id_competencias) VALUES (?, ?);"
        try {
            PreparedStatement stmtAssociacao = con.prepareStatement(sqlAssociacao)
            stmtAssociacao.setInt(1, vagaId)
            stmtAssociacao.setInt(2, idCompetencia)
            stmtAssociacao.executeUpdate()
        } catch (SQLException e) {
            e.printStackTrace()
        }
        ConexaoDAO.desconectar(con)
    }

    static int consultarIdEmpresa(String nomeEmpresa) {
        Connection con = new ConexaoDAO().getConnection()

        String sql = "SELECT id FROM empresas WHERE nome = ?"
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            stmt.setString(1, nomeEmpresa)
            ResultSet resultado = stmt.executeQuery()

            if (resultado.next()) {
                return resultado.getInt("id")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }
        return -1 // Retorna -1 se a empresa não for encontrada
    }
}
