package Model

class EmpresaModel extends PessoaModel {
    int id
    String cnpj
    String descricao

    EmpresaModel(int id, String nome, String email, String cep, String cnpj, String descricao) {
        super(nome, email, cep)
        this.id = id
        this.cnpj = cnpj
        this.descricao = descricao
    }
}