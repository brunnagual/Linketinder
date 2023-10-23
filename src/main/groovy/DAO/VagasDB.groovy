package DAO


import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class VagasDB{
static void listarVagas() {

    Connection con = new Conexao().getConnection()

    String sql = "SELECT v.id, v.nome, v.descricao, v.salario, e.nome AS empresa FROM vagas AS v, empresas AS e WHERE v.id_empresa = e.id;"
    ResultSet res = null

    try {
        PreparedStatement stmt = con.prepareStatement(sql)
        res = stmt.executeQuery()

        while (res.next()) {
            String id = res.getString("ID")
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
    Conexao.desconectar (con)
}

    static void exibirVagas(ResultSet res) {
        try {
            while (res.next()) {
                String id = res.getString("ID")
                String nome = res.getString("nome")
                String descricao = res.getString("descricao")
                String salario = res.getString("salario")
                String nomeEmpresa = res.getString("empresa")

                println("$id | $nome | $descricao | $salario | $nomeEmpresa")
            }
        } catch (SQLException e) {
            handleSQLException(e)
        }
    }

static void cadastrarVaga(List<DAO.VagasDB> vagas, Scanner scanner) {
    Connection con = new Conexao().getConnection()

    String nome = capturarEntrada("Nome da Vaga: ", scanner)
    String descricao = capturarEntrada("Descrição da Vaga: ", scanner)
    String salarioStr = capturarEntrada("Salario: ", scanner)
    double salario = salarioStr.toDouble()
    String nomeEmpresa = capturarEntrada("Nome dá Empresa: ", scanner)
    int idEmpresa = consultarIdEmpresa(nomeEmpresa)

    listarCompetenciasDisponiveis()

    int idCompetencia = capturarEntrada("Digite o número da competência que deseja associar (ou F para finalizar e cadastrar vaga)): ", scanner) as int

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
        stmtVaga.setInt(5,idCompetencia)

        int rowsAffected = stmtVaga.executeUpdate()
        if (rowsAffected > 0) {
            ResultSet generatedKeys = stmtVaga.getGeneratedKeys()
            int vagaId = -1

            if (generatedKeys.next()) {
                vagaId = generatedKeys.getInt(1)
            }
            for (int idComp : competenciasAssociadas) {
                associarCompetencia(vagaId, idCompetencia)
            }
            println("Vaga cadastrada com sucesso.")
        } else {
            println("Falha ao cadastrar a Vaga.")
        }
    } catch (Exception e) {
        e.printStackTrace()
    }
    Conexao.desconectar (con)
}

static void listarCompetenciasDisponiveis() {
    Connection con = new Conexao().getConnection()

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
    Conexao.desconectar (con)
}

static boolean verificarCompetenciaExistente(int idCompetencia) {
    Connection con = new Conexao().getConnection()

    String sqlVerificar = "SELECT id FROM competencias WHERE id = ?"
    try {
        PreparedStatement stmtVerificar = con.prepareStatement(sqlVerificar)
        stmtVerificar.setInt(1, idCompetencia)
        ResultSet resultado = stmtVerificar.executeQuery()

        return resultado.next()
    } catch (SQLException e) {
        e.printStackTrace()
    }
    return false
    Conexao.desconectar (con)
}

static void associarCompetencia(int vagaId, int idCompetencia) {
    Connection con = new Conexao().getConnection()

    String sqlAssociacao = "INSERT INTO vagas_competencias (id_vagas, id_competencias) VALUES (?, ?);"
    try {
        PreparedStatement stmtAssociacao = con.prepareStatement(sqlAssociacao)
        stmtAssociacao.setInt(1, vagaId)
        stmtAssociacao.setInt(2, idCompetencia)
        stmtAssociacao.executeUpdate()
    } catch (SQLException e) {
        e.printStackTrace()
    }
    Conexao.desconectar (con)
}

static int consultarIdEmpresa(String nomeEmpresa) {
    Connection con = new Conexao().getConnection()

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
    Conexao.desconectar (con)
}

    static String capturarEntrada(String mensagem, Scanner scanner) {
        print(mensagem)
        return scanner.nextLine()
    }
}