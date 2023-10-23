package Users

class Vagas {
    String nome
    String descricao
    double salario

    Vagas(String nome, String descricao, double salario) {
        this.nome = nome
        this.descricao = descricao
        this.salario = salario
    }

    static void adicionarVaga(vagas, scanner) {

        String nome = Regex.validarEntrada(Regex.regexNome, "Nome da vaga: ", scanner)
        String descricao = Regex.validarEntrada(Regex.regexDrescricao, "Descrição da vaga: ", scanner)
        double salario = Regex.validarEntrada(Regex.regexSalario, "Salário: ", scanner) as double
        String empresa = Regex.validarEntrada(Regex.regexNome, "Empresa: ", scanner)

        Vagas vaga = new Vagas(nome, descricao, salario, empresa);
        vagas.add(vaga)
    }
}
