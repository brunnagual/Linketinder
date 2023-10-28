package Controller

import Model.CandidatoModel
import DAO.CandidatoDAO
import Model.RegexModel

class CandidatoController {
    static CandidatoModel cadastrarCandidato(List<CandidatoModel> candidatos, Scanner scanner) {

        String nome = validarEntrada("Nome: ", RegexModel.regexNome, scanner)
        String sobrenome = validarEntrada("Sobrenome: ", RegexModel.regexNome, scanner)
        String email = validarEntrada("Email: ", RegexModel.regexEmail, scanner)
        long cpf = validarEntradaLong("CPF: ", RegexModel.regexCpf, scanner)
        String cep = validarEntrada("CEP: ", RegexModel.regexCep, scanner)
        String descricaoPessoal = validarEntrada("Descrição Pessoal: ", RegexModel.regexDrescricao, scanner)

        ArrayList<String> competencias = new ArrayList<String>()
        CandidatoModel candidato = new CandidatoModel(nome, sobrenome, email, cep, cpf, descricaoPessoal, competencias)

        CandidatoDAO.cadastrarCandidato(candidato, scanner)

        return candidato
    }

    static String validarEntrada(String mensagem, String regex, Scanner scanner) {
        String entrada
        boolean entradaValida = false

        while (!entradaValida) {
            print(mensagem)
            entrada = scanner.nextLine().trim()
            if (entrada.matches(regex)) {
                entradaValida = true
            } else {
                println("Entrada inválida. Tente novamente.")
            }
        }

        return entrada
    }

    static long validarEntradaLong(String mensagem, String regex, Scanner scanner) {
        long entrada
        boolean entradaValida = false

        while (!entradaValida) {
            print(mensagem)
            try {
                entrada = Long.parseLong(scanner.nextLine().trim())
                if (String.valueOf(entrada).matches(regex)) {
                    entradaValida = true
                } else {
                    println("Entrada inválida. Tente novamente.")
                }
            } catch (NumberFormatException e) {
                println("Entrada inválida. Deve ser um número válido.")
            }
        }

        return entrada
    }


    static int capturarEntradaInt(String mensagem, Scanner scanner) {
        print(mensagem)
        return scanner.nextInt()
    }

    static void mostrarSucesso(String tipo) {
        println("$tipo cadastrado com sucesso.")
    }
}