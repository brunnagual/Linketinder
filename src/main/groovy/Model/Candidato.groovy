package Model

import DAO.CandidatoDB
import DAO.Conexao

import java.sql.Connection

class Candidato extends Pessoa {
    String sobrenome
    String descricaoPessoal
    long cpf

    ArrayList<String> listarTodasCompetencias = new ArrayList<String>()

    Candidato(String nome, String sobrenome, String email, String cep, long cpf, String descricaoPessoal, ArrayList<String> competencias) {
        super(nome, email, cep)
        this.sobrenome = sobrenome
        this.descricaoPessoal = descricaoPessoal
        this.cpf = cpf
        this.listarTodasCompetencias = competencias
    }

    static void cadastrarCandidato(List<Candidato> candidatos, Scanner scanner) {
        Connection con = new Conexao().getConnection()

        try {
            String nome = Regex.validarEntrada(Regex.regexNome, "Nome: ", scanner)
            String sobrenome = Regex.validarEntrada(Regex.regexNome, "Sobrenome: ", scanner)
            String email = Regex.validarEntrada(Regex.regexEmail, "Email: ", scanner)
            long cpf = Long.parseLong(Regex.validarEntrada(Regex.regexCpf, "CPF: ", scanner))
            String cep = Regex.validarEntrada(Regex.regexCep, "CEP: ", scanner)
            String descricaoPessoal = Regex.validarEntrada(Regex.regexDrescricao, "Descrição Pessoal: ", scanner)

            ArrayList<String> competencias = new ArrayList<String>()
            Candidato candidato = new Candidato(nome, sobrenome, email, cep, cpf, descricaoPessoal, competencias)
            candidatos.add(candidato)
            CandidatoDB.cadastrarCandidato(candidato, scanner)
        } finally {
            Conexao.desconectar(con)
        }
    }
}