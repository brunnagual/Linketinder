package DAO

import Users.Candidato

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement


//----------------------------- Lista Candidatos --------------------------
static void listarCandidatos(Connection con) {
    String sql = "SELECT * FROM candidatos"
    ResultSet res = null
    try {
        PreparedStatement stmt = con.prepareStatement(sql)
        res = stmt.executeQuery()

        while (res.next()) {
            String nome = res.getString("nome")
            String sobrenome = res.getString("sobrenome")
            String email = res.getString("email")
            String cep = res.getString("cep")
            String descricaoPessoal = res.getString("descricao")

            println("$nome | $sobrenome | $email | $cep | $descricaoPessoal")
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
}

//-------------------------------- Cadastro Candidato -------------------------------------
static void cadastrarCandidato(List<Candidato> candidatos, Connection con, Scanner scanner) {
    def nome = capturarEntrada("Nome: ", scanner)
    def sobrenome = capturarEntrada("Sobrenome: ", scanner)
    def email = capturarEntrada("Email: ", scanner)
    def cep = capturarEntrada("CEP: ", scanner)
    def cpf = capturarEntrada("CPF: ", scanner) as long
    def descricaoPessoal = capturarEntrada("Descrição Pessoal: ", scanner)

    // Instrução SQL para inserir o candidato
    String sqlCandidato = "INSERT INTO public.candidatos(nome, sobrenome, email, cep, cpf, pais, descricao, senha) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?);"

    try {
        PreparedStatement stmtCandidato = con.prepareStatement(sqlCandidato, Statement.RETURN_GENERATED_KEYS)
        stmtCandidato.setString(1, nome)
        stmtCandidato.setString(2, sobrenome)
        stmtCandidato.setString(3, email)
        stmtCandidato.setString(4, cep)
        stmtCandidato.setLong(5, cpf)
        stmtCandidato.setString(6, "Brasil")
        stmtCandidato.setString(7, descricaoPessoal)
        stmtCandidato.setString(8, "senha_padrao") // Define a senha padrão, ajuste conforme necessário

        int rowsAffected = stmtCandidato.executeUpdate()
        if (rowsAffected > 0) {
            ResultSet generatedKeys = stmtCandidato.getGeneratedKeys()
            int candidatoId = -1

            if (generatedKeys.next()) {
                candidatoId = generatedKeys.getInt(1)
            }

            // Listar todas as competências disponíveis
            listarTodasCompetencias(con)

            // Associar competências ao candidato em um loop
            while (true) {
                associarCompetencia(candidatoId, con, scanner)

                println("Deseja associar outra competência? (S/N): ")
                def resposta = scanner.nextLine().toUpperCase()
                if (!resposta.equals("S")) {
                    break
                }
            }

            println("Candidato cadastrado com sucesso.")
        } else {
            println("Falha ao cadastrar o candidato.")
        }
    } catch (Exception e) {
        e.printStackTrace()
    }
}

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

static void associarCompetencia(int candidatoId, Connection con, Scanner scanner) {
    def idCompetencia = capturarEntrada("Digite o número da competência que deseja associar: ", scanner) as int

    // Verificar se a competência existe
    String sqlVerificar = "SELECT * FROM competencias WHERE id = ?"
    try {
        PreparedStatement stmtVerificar = con.prepareStatement(sqlVerificar)
        stmtVerificar.setInt(1, idCompetencia)
        ResultSet resultado = stmtVerificar.executeQuery()

        if (resultado.next()) {
            // Inserir a associação na tabela candidatos_competencias
            String sqlAssociacao = "INSERT INTO public.candidatos_competencias(id_candidatos, id_competencias) VALUES (?, ?);"
            PreparedStatement stmtAssociacao = con.prepareStatement(sqlAssociacao)
            stmtAssociacao.setInt(1, candidatoId)
            stmtAssociacao.setInt(2, idCompetencia)
            stmtAssociacao.executeUpdate()

            println("Competência associada com sucesso.")
        } else {
            println("Competência não encontrada. Tente novamente.")
        }
    } catch (SQLException e) {
        e.printStackTrace()
    }
}

static String capturarEntrada(String mensagem, Scanner scanner) {
    print(mensagem)
    return scanner.nextLine()
}