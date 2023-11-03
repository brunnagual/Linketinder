package Controller
import DAO.ConexaoDAO
import DAO.EmpresaDAO
import Model.EmpresaModel

import java.sql.Connection

class EmpresaController {

    static boolean cadastrarEmpresa(EmpresaModel empresa, Scanner scanner) {
        Connection con = ConexaoDAO.getInstance().getConnection()

        int empresaId = EmpresaDAO.inserirEmpresaNoBanco(empresa.nome, empresa.email, empresa.cep, empresa.cnpj, empresa.descricao)

    }
    static List<EmpresaModel> listarEmpresas(){
        return EmpresaDAO.listarEmpresas()
    }
}
