package Model

class VagasModel {
    String nome
    String descricao
    double salario
    int nomeEmpresa

    VagasModel(String nome, String descricao, double salario, int nomeEmpresa) {
        this.nome = nome
        this.descricao = descricao
        this.salario = salario
        this.nomeEmpresa = nomeEmpresa
    }

    static void adicionarVaga(Scanner scanner) {

        String nome = RegexModel.validarEntrada(RegexModel.regexNome, "Nome da vaga: ", scanner)
        String descricao = RegexModel.validarEntrada(RegexModel.regexDrescricao, "Descrição da vaga: ", scanner)
        double salario = RegexModel.validarEntrada(RegexModel.regexSalario, "Salário: ", scanner) as double
        String empresa = RegexModel.validarEntrada(RegexModel.regexNome, "Empresa: ", scanner)

        VagasModel vaga = new VagasModel(nome, descricao, salario, empresa)

    }
}
