package DAO


import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import Model.Empresa

import java.sql.Statement

class EmpresaDB {

    static void listarEmpresas() {
        Connection con = new Conexao().getConnection()

        String sql = "SELECT * FROM empresas"
        ResultSet conjuntoResultados = null
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            conjuntoResultados = stmt.executeQuery()
            exibirEmpresas(conjuntoResultados)
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            fecharConjuntoResultados(conjuntoResultados)
        }
        Conexao.desconectar (con)
    }

    static void cadastrarEmpresa(Empresa empresas, Scanner scanner) {
        Connection con = new Conexao().getConnection()

        String nome = empresas.nome
        String email = empresas.email
        String cep = empresas.cep
        String cnpj = empresas.cnpj
        String descricao = empresas.descricao

        if (inserirEmpresaNoBanco(nome, email, cep, cnpj, descricao)) {
            println("Empresa cadastrada com sucesso.")
        } else {
            println("Falha ao cadastrar a empresa.")
        }
        Conexao.desconectar (con)
    }

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

    static boolean inserirEmpresaNoBanco(String nome, String email, String cep, String cnpj, String descricao) {
        Connection con = new Conexao().getConnection()
        String sqlInserirEmpresa = "INSERT INTO public.empresas(nome, email, cep, cnpj, pais, descricao, senha, vagas) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"

        try {
            PreparedStatement stmt = con.prepareStatement(sqlInserirEmpresa)
            stmt.setString(1, nome)
            stmt.setString(2, email)
            stmt.setString(3, cep)
            stmt.setString(4, cnpj)
            stmt.setString(5, "Brasil")
            stmt.setString(6, descricao)
            stmt.setString(7, "senha_padrao")
            stmt.setInt(8, 0)

            int rowsAffected = stmt.executeUpdate()
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace()
            return false;
        }
        Conexao.desconectar (con)
    }

    static String capturarEntrada(String mensagem, Scanner scanner) {
        print(mensagem)
        return scanner.nextLine()
    }

    static void fecharConjuntoResultados(ResultSet conjuntoResultados) {
        if (conjuntoResultados != null) {
            try {
                conjuntoResultados.close()
            } catch (SQLException e) {
                e.printStackTrace()
            }
        }
    }
}
