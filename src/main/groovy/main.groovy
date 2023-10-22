import DAO.CandidatoDB
import DAO.EmpresaDB
import DAO.conexao
import DAO.VagasDB
import DAO.competenciasDB
import Users.Candidato
import Users.Empresa

import java.sql.Connection

static void main(String[] args) {
    Connection con = new conexao().getConnection()
    List<Candidato> candidatos = []
    List<Empresa> empresas = []
    List<VagasDB> vagas = []
    Scanner scanner = new Scanner(System.in)

    menu: while (true) {
        println("Escolha uma opção:\n")
        println("[1] Listar Candidatos")
        println("[2] Listar Empresas")
        println("[3] Cadastrar Candidato")
        println("[4] Cadastrar Empresas")
        println("[5] Cadastrar Vaga")
        println("[6] Listar Vagas")
        println("[7] Cadastrar competências")
        println("[0] Sair")
        String opcao = scanner.nextLine()

        switch (opcao) {
            case "1":
                CandidatoDB.listarCandidatos(con)
                break
            case "2":
                EmpresaDB.listarEmpresas(con)
                break
            case "3":
                CandidatoDB.cadastrarCandidato(candidatos,con, scanner)
                break
            case "4":
                EmpresaDB.cadastrarEmpresa(empresas,con, scanner)
                break
            case "5":
                VagasDB.cadastrarVaga(vagas,con, scanner)
                break
            case "6":
                VagasDB.listarVagas(con)
                break
            case "7":
                competenciasDB.cadastrarCompetencia(con, scanner)
                break
            case "0":
                println("Saindo do programa.")
                break menu
            default:
                println("Opção inválida.")
        }
    }
}