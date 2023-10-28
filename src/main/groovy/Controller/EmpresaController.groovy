package Controller

import DAO.EmpresaDAO
import Model.EmpresaModel
import Model.RegexModel

import java.sql.ResultSet
import java.sql.SQLException

class EmpresaController {
    static EmpresaModel cadastrarEmpresa(List<EmpresaModel> empresas, Scanner scanner) {

            String nome = RegexController.validarEntrada("Nome: ", RegexModel.regexNome, scanner)
            String email = RegexController.validarEntrada( "Email: ",RegexModel.regexEmail, scanner)
            String cep = RegexController.validarEntrada( "CEP: ",RegexModel.regexCep, scanner)
            String cnpj = RegexController.validarEntrada( "CNPJ: ",RegexModel.regexCnpj, scanner)
            String descricao = RegexController.validarEntrada( "Descrição: ",RegexModel.regexDrescricao, scanner);

            EmpresaModel empresa = new EmpresaModel(nome, email, cep, cnpj, descricao);
            empresas.add(empresa);
            EmpresaDAO.cadastrarEmpresa(empresa, scanner);

        return empresa
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
