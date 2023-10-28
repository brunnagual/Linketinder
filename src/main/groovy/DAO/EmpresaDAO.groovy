package DAO

import Controller.EmpresaController
import View.EmpresaView

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

import Model.EmpresaModel

class EmpresaDAO {

    static void listarEmpresas() {
        Connection con = new ConexaoDAO().getConnection()

        String sql = "SELECT * FROM empresas"
        ResultSet conjuntoResultados = null
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            conjuntoResultados = stmt.executeQuery()
            EmpresaView.exibirEmpresas(conjuntoResultados)
        } catch (SQLException e) {
            e.printStackTrace()
        } finally {
            EmpresaController.fecharConjuntoResultados(conjuntoResultados)
        }
        ConexaoDAO.desconectar (con)
    }

    static void cadastrarEmpresa(EmpresaModel empresas, Scanner scanner) {
        Connection con = new ConexaoDAO().getConnection()

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
        ConexaoDAO.desconectar (con)
    }

    static boolean inserirEmpresaNoBanco(String nome, String email, String cep, String cnpj, String descricao) {
        Connection con = new ConexaoDAO().getConnection()
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
        ConexaoDAO.desconectar (con)
    }

}
