package DAO


import Model.EmpresaModel

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

class EmpresaDAO {

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
