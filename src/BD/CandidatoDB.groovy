package BD

import Users.Candidato

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException


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
//    String sqlCompetencia = "INSERT INTO public.candidatos_competencias(id_candidatos, id_competencias) VALUES (?, ?);"

    try {
        PreparedStatement stmt = con.prepareStatement(sqlCandidato)
        stmt.setString(1, nome)
        stmt.setString(2, sobrenome)
        stmt.setString(3, email)
        stmt.setString(4, cep)
        stmt.setLong(5, cpf)
        stmt.setString(6, "Brasil")
        stmt.setString(7, descricaoPessoal)
        stmt.setString(8, "senha_padrao") // Define a senha padrão, ajuste conforme necessário

        int rowsAffected = stmt.executeUpdate()
        if (rowsAffected > 0) {
            println("Candidato cadastrado com sucesso.")
        } else {
            println("Falha ao cadastrar o candidato.")
        }
    } catch (Exception e) {
        e.printStackTrace()
    }

}

static String capturarEntrada(String mensagem, Scanner scanner) {
    print(mensagem)
    return scanner.nextLine()
}