package Users

class Candidato extends Pessoa {
    String descricaoPessoal
    int cpf
    int idade
    List<String> competencias = []

    Candidato(String nome, String email, int idade, String estado, int cep, long cpf, String descricaoPessoal, List<String> competencias) {
        super(nome, email, idade, estado, cep)
        this.descricaoPessoal = descricaoPessoal
        this.idade = idade
        this.cpf = cpf
        this.competencias = competencias
    }

}
