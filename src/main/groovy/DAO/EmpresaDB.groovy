package DAO

import Users.Empresa

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException


//----------------------------- Lista Empresa --------------------------
static void listarEmpresas(Connection con) {
    String sql = "SELECT * FROM empresas"
    ResultSet res = null
    try {
        PreparedStatement stmt = con.prepareStatement(sql)
        res = stmt.executeQuery()

        while (res.next()) {
            String nome = res.getString("nome")
            String email = res.getString("email")
            String cep = res.getString("cep")
            String descricao = res.getString("descricao")

            println("$nome | $email | $cep | $descricao")
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

//-------------------------------- Cadastro Empresa -------------------------------------
static void cadastrarEmpresa(List<Empresa> empresas, Connection con, Scanner scanner) {
    def nome = capturarEntrada("Nome: ", scanner)
    def email = capturarEntrada("E-mail: ", scanner)
    def cep = capturarEntrada("CEP: ", scanner)
    def cnpj = capturarEntrada("CNPJ: ", scanner)
    def descricao = capturarEntrada("Descrição: ", scanner)

    // Instrução SQL para inserir o empresa
    String sql = "INSERT INTO public.empresas(nome, email, cep, cnpj, pais, descricao, senha, vagas) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"

    try {
        PreparedStatement stmt = con.prepareStatement(sql)
        stmt.setString(1, nome)
        stmt.setString(2, email)
        stmt.setString(3, cep)
        stmt.setString(4, cnpj)
        stmt.setString(5, "Brasil")
        stmt.setString(6, descricao)
        stmt.setString(7, "senha_padrao") // Define a senha padrão, ajuste conforme necessário
        stmt.setInt(8, 0) // Defina a quantidade inicial de vagas (ajuste conforme necessário)

        int rowsAffected = stmt.executeUpdate()
        if (rowsAffected > 0) {
            println("Empresa cadastrada com sucesso.")
        } else {
            println("Falha ao cadastrar a empresa.")
        }
    } catch (Exception e) {
        e.printStackTrace()
    }
}

static String capturarEntrada(String mensagem, Scanner scanner) {
    print(mensagem)
    return scanner.nextLine()
}