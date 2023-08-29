package Users

class Empresa extends Pessoa{
    String cnpj
    List<String> expectativasCompetencias = []

    Empresa(String nome, String email, int idade, String estado, int cep, String cnpj, List<String> expectativasCompetencias) {
        super(nome, email, idade, estado, cep)
        this.cnpj = cnpj
        this.expectativasCompetencias = expectativasCompetencias
    }
}
