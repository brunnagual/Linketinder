package Model

import DAO.CompetenciasDB

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
        CompetenciasDB.cadastrarCompetencia(nomeCompetencia)
    }
}
