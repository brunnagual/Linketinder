package Model

class VagasModel {
    String nome
    String descricao
    double salario
    Integer idEmpresa
    String nomeEmpresa
    ArrayList<String> listarTodasCompetenciasVagas = new ArrayList<String>()

    VagasModel(String nome, String descricao, double salario,int idEmpresa, String nomeEmpresa, ArrayList<String> competenciasVagas) {
        this.nome = nome
        this.descricao = descricao
        this.salario = salario
        this.idEmpresa = idEmpresa
        this.nomeEmpresa = nomeEmpresa
        this.listarTodasCompetenciasVagas = competenciasVagas

    }
}