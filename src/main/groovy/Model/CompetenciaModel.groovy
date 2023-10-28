package Model

import DAO.CompetenciasDAO

class CompetenciaModel {
    String nomeCompetencias

    CompetenciaModel(String nomeCompetencias) {
        this.nomeCompetencias = nomeCompetencias
    }

    static void cadastrarCompetencia(Scanner scanner){
        println("Digite o nome dá competência: ")
        String nomeCompetencia = scanner.nextLine()
        while(!RegexModel.validarEntrada(RegexModel.regexNome, nomeCompetencia)){
            println("Competência invalida")
            nomeCompetencia = scanner.nextLine()
        }
        CompetenciasDAO.cadastrarCompetencia(nomeCompetencia)
    }
}
