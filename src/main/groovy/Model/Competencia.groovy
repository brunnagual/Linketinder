package Model

import DAO.competenciasDB

class Competencia {
    String nomeCompetencias

    Competencia(String nomeCompetencias) {
        this.nomeCompetencias = nomeCompetencias
    }

    static void cadastrarCompetencia(Scanner scanner){
        println("Digite o nome dá competência: ")
        String nomeCompetencia = scanner.nextLine()
        while(!Regex.validarEntrada(Regex.regexNome, nomeCompetencia)){
            println("Competência invalida")
            nomeCompetencia = scanner.nextLine()
        }
        competenciasDB.cadastrarCompetencia(nomeCompetencia)
    }
}
