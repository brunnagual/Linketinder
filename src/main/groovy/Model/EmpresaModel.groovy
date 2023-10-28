package Model

import DAO.ConexaoDAO
import DAO.EmpresaDAO

import java.sql.Connection

class EmpresaModel extends PessoaModel {
    String cnpj
    String descricao

    EmpresaModel(String nome, String email, String cep, String cnpj, String descricao) {
        super(nome, email, cep)
        this.cnpj = cnpj
        this.descricao = descricao
    }

    static void cadastrarEmpresa(List<EmpresaModel> empresas, Scanner scanner) {
        Connection con = new ConexaoDAO().getConnection()

        try {
            String nome = RegexModel.validarEntrada(RegexModel.regexNome, "Nome: ", scanner)
            String email = RegexModel.validarEntrada(RegexModel.regexEmail, "Email: ", scanner)
            String cep = RegexModel.validarEntrada(RegexModel.regexCep, "CEP: ", scanner)
            String cnpj = RegexModel.validarEntrada(RegexModel.regexCnpj, "CNPJ: ", scanner)
            String descricao = RegexModel.validarEntrada(RegexModel.regexDrescricao, "Descrição: ", scanner);

            EmpresaModel empresa = new EmpresaModel(nome, email, cep, cnpj, descricao);
            empresas.add(empresa);
            EmpresaDAO.cadastrarEmpresa(empresa, scanner);
        } finally {
            ConexaoDAO.desconectar(con)
        }
    }
}