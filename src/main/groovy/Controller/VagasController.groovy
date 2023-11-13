package Controller


import DAO.ConexaoDAO
import DAO.VagasDAO
import Model.VagasModel

import java.sql.Connection

class VagasController {

    static Double cadastrarVaga(VagasModel vaga, Scanner scanner) {
        Connection con = ConexaoDAO.getInstance().getConnection()
        int vagaId = VagasDAO.inserirVagasNoBanco(vaga)

        return vagaId
    }
    static List<VagasModel> ListarVagas(){
        return VagasDAO.listarVagas()
    }

    static Boolean associarCompetencias(Integer vagaId, Integer idCompetencia){
        return VagasDAO.associarCompetencia(vagaId, idCompetencia)
    }
}
