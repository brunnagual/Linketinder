package Controller
import DAO.ConexaoDAO
import DAO.EmpresaDAO
import Model.EmpresaModel

import java.sql.Connection

class EmpresaController {
//    static EmpresaModel cadastrarEmpresa(List<EmpresaModel> empresas, Scanner scanner) {
//
//            String nome = RegexController.validarEntrada("Nome: ", RegexModel.regexNome, scanner)
//            String email = RegexController.validarEntrada( "Email: ",RegexModel.regexEmail, scanner)
//            String cep = RegexController.validarEntrada( "CEP: ",RegexModel.regexCep, scanner)
//            String cnpj = RegexController.validarEntrada( "CNPJ: ",RegexModel.regexCnpj, scanner)
//            String descricao = RegexController.validarEntrada( "Descrição: ",RegexModel.regexDrescricao, scanner);
//
//            EmpresaModel empresa = new EmpresaModel(nome, email, cep, cnpj, descricao);
//            empresas.add(empresa);
//            EmpresaDAO.cadastrarEmpresa(empresa, scanner);
//
//        return empresa
//    }
//
//    static void fecharConjuntoResultados(ResultSet conjuntoResultados) {
//        if (conjuntoResultados != null) {
//            try {
//                conjuntoResultados.close()
//            } catch (SQLException e) {
//                e.printStackTrace()
//            }
//        }
//    }

    static boolean cadastrarEmpresa(EmpresaModel empresa, Scanner scanner) {
        Connection con = ConexaoDAO.getInstance().getConnection()

        int empresaId = EmpresaDAO.inserirEmpresaNoBanco(empresa.nome, empresa.email, empresa.cep, empresa.cnpj, empresa.descricao)

    }
    static List<EmpresaModel> listarEmpresas(){
        return EmpresaDAO.listarEmpresas()
    }
}
