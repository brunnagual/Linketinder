package Model

class EmpresaModel extends PessoaModel {
    String cnpj
    String descricao

    EmpresaModel(String nome, String email, String cep, String cnpj, String descricao) {
        super(nome, email, cep)
        this.cnpj = cnpj
        this.descricao = descricao
    }
}