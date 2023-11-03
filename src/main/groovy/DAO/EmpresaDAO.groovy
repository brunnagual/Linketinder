package DAO


import Model.CandidatoModel
import Model.EmpresaModel

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class EmpresaDAO {

//    static void listarEmpresas() {
//        Connection con = new ConexaoDAO().getConnection()
//
//        String sql = "SELECT * FROM empresas"
//        ResultSet conjuntoResultados = null
//        try {
//            PreparedStatement stmt = con.prepareStatement(sql)
//            conjuntoResultados = stmt.executeQuery()
//            EmpresaView.exibirEmpresas(conjuntoResultados)
//        } catch (SQLException e) {
//            e.printStackTrace()
//        } finally {
//            EmpresaController.fecharConjuntoResultados(conjuntoResultados)
//        }
//        ConexaoDAO.desconectar (con)
//    }
//
//    static void cadastrarEmpresa(EmpresaModel empresas, Scanner scanner) {
//        Connection con = new ConexaoDAO().getConnection()
//
//        String nome = empresas.nome
//        String email = empresas.email
//        String cep = empresas.cep
//        String cnpj = empresas.cnpj
//        String descricao = empresas.descricao
//
//        if (inserirEmpresaNoBanco(nome, email, cep, cnpj, descricao)) {
//            println("Empresa cadastrada com sucesso.")
//        } else {
//            println("Falha ao cadastrar a empresa.")
//        }
//        ConexaoDAO.desconectar (con)
//    }
//
//    static boolean inserirEmpresaNoBanco(String nome, String email, String cep, String cnpj, String descricao) {
//        Connection con = new ConexaoDAO().getConnection()
//        String sqlInserirEmpresa = "INSERT INTO public.empresas(nome, email, cep, cnpj, pais, descricao, senha, vagas) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"
//
//        try {
//            PreparedStatement stmt = con.prepareStatement(sqlInserirEmpresa)
//            stmt.setString(1, nome)
//            stmt.setString(2, email)
//            stmt.setString(3, cep)
//            stmt.setString(4, cnpj)
//            stmt.setString(5, "Brasil")
//            stmt.setString(6, descricao)
//            stmt.setString(7, "senha_padrao")
//            stmt.setInt(8, 0)
//
//            int rowsAffected = stmt.executeUpdate()
//            return rowsAffected > 0;
//
//        } catch (Exception e) {
//            e.printStackTrace()
//            return false;
//        }
//    }

    private static final String SQL_INSERIR_EMPRESAS =
    "INSERT INTO public.empresas(nome, email, cep, cnpj, descricao) VALUES (?, ?, ?, ?, ?);"

    private static final String SQL_SELECT_ALL_EMPRESAS =
            "SELECT nome, email, cep, cnpj, descricao, id  FROM empresas"


    static List<EmpresaModel> listarEmpresas() {

        Connection con = ConexaoDAO.getInstance().getConnection()

        ResultSet conjuntoResultados = null
        try {
            PreparedStatement stmt = con.prepareStatement(SQL_SELECT_ALL_EMPRESAS)
            conjuntoResultados = stmt.executeQuery()

            List<EmpresaModel> ListaEmpresas = []
            while (conjuntoResultados.next()) {

                String nome = conjuntoResultados.getString(1)
                String email = conjuntoResultados.getString(2)
                String cep = conjuntoResultados.getString(3)
                String cnpj = conjuntoResultados.getString(4)
                String descricao = conjuntoResultados.getString(5)
                int id = conjuntoResultados.getInt(6)

                EmpresaModel empresa = new EmpresaModel(id, nome, email, cep, cnpj, descricao)
                ListaEmpresas << empresa
            }
            return ListaEmpresas

        } catch (SQLException e) {
            DatabaseUtilDAO.handleSQLException(e)
        }
    }

    static int inserirEmpresaNoBanco(String nome, String email, String cep, String cnpj, String descricao) {

        Connection con = ConexaoDAO.getInstance().getConnection()
        try {
            PreparedStatement stmtEmpresa = con.prepareStatement(SQL_INSERIR_EMPRESAS, Statement.RETURN_GENERATED_KEYS)
            stmtEmpresa.setString(1, nome)
            stmtEmpresa.setString(2, email)
            stmtEmpresa.setString(3, cep)
            stmtEmpresa.setString(4, cnpj)
            stmtEmpresa.setString(5, descricao)

            int rowsAffected = stmtEmpresa.executeUpdate()
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmtEmpresa.getGeneratedKeys()
                int empresaId = -1

                if (generatedKeys.next()) {
                    empresaId = generatedKeys.getInt(1)
                }

                return empresaId
            }
        } catch (SQLException e) {
            DatabaseUtilDAO.handleSQLException(e)
        }
        return -1
    }

}
