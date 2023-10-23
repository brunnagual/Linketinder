package Users

import DAO.EmpresaDB

import java.sql.Connection

class Empresa extends Pessoa{
    String cnpj
    String descricao

    Empresa(String nome, String email, String cep, String cnpj, String descricao) {
        super(nome, email, cep)
        this.cnpj = cnpj
        this.descricao = descricao
    }

    static void cadastrarEmpresa(List<Empresa> empresas, Connection con ,Scanner scanner) {

        String nome = Regex.validarEntrada(Regex.regexNome, "Nome: ", scanner)
        String email = Regex.validarEntrada(Regex.regexEmail, "Email: ", scanner)
        String cep = Regex.validarEntrada(Regex.regexCep, "CEP: ", scanner)
        String cnpj = Regex.validarEntrada(Regex.regexCnpj, "CNPJ: ", scanner)
        String descricao = Regex.validarEntrada(Regex.regexDrescricao, "Descrição: ", scanner);

        Empresa empresa = new Empresa(nome, email, cep, cnpj,descricao);
        empresas.add(empresa);
        EmpresaDB.cadastrarEmpresa(empresa, con, scanner);
    }
}
