package Users

class Empresa extends Pessoa{
    String cnpj
    List<String> expectativasCompetencias = []

    Empresa(String nome, String email, int cep, String pais, String cnpj, List<String> expectativasCompetencias) {
        super(nome, email, cep, pais)
        this.cnpj = cnpj
        this.expectativasCompetencias = expectativasCompetencias
    }
}
