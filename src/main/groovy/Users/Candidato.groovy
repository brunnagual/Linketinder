package Users

class Candidato extends Pessoa {
    String descricaoPessoal
    int cpf
    List<String> competencias = []

    Candidato(String nome, String email, String pais, int cep, long cpf, String descricaoPessoal, List<String> competencias) {
        super(nome, email, pais, cep)
        this.descricaoPessoal = descricaoPessoal
        this.cpf = cpf
        this.competencias = competencias
    }

}
