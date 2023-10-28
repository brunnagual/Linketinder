package Model

class CandidatoModel extends PessoaModel {
    String sobrenome
    String descricaoPessoal
    long cpf
    ArrayList<String> listarTodasCompetencias = new ArrayList<String>()

    CandidatoModel(String nome, String sobrenome, String email, String cep, long cpf, String descricaoPessoal, ArrayList<String> competencias) {
        super(nome, email, cep)
        this.sobrenome = sobrenome
        this.descricaoPessoal = descricaoPessoal
        this.cpf = cpf
        this.listarTodasCompetencias = competencias
    }
}