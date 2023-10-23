package Model

class Vagas {
    String nome
    String descricao
    double salario
    int nomeEmpresa

    Vagas(String nome, String descricao, double salario, int nomeEmpresa) {
        this.nome = nome
        this.descricao = descricao
        this.salario = salario
        this.nomeEmpresa = nomeEmpresa
    }

    static void adicionarVaga(Scanner scanner) {

        String nome = Regex.validarEntrada(Regex.regexNome, "Nome da vaga: ", scanner)
        String descricao = Regex.validarEntrada(Regex.regexDrescricao, "Descrição da vaga: ", scanner)
        double salario = Regex.validarEntrada(Regex.regexSalario, "Salário: ", scanner) as double
        String empresa = Regex.validarEntrada(Regex.regexNome, "Empresa: ", scanner)

        Vagas vaga = new Vagas(nome, descricao, salario, empresa)

    }
}
