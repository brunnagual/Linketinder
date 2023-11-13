package Controller

import DAO.VagasDAO
import Model.VagasModel
import DAO.ConexaoDAO
import java.sql.Connection
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import com.google.gson.Gson

@WebServlet("/vaga")
class VagasController extends HttpServlet{
    private static final long serialVersionUID = 1L
    private Gson gson = new Gson()

    VagasDAO vagasDao = new VagasDAO()

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<VagasModel> vagas = vagasDao.listarVagas()

            String jsonVagas = convertToJSON(vagas);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonVagas);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao obter a lista de vagas.");
        }
    }

    private static String convertToJSON(List<VagasModel> vagas) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(vagas);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String corpoRequisicao = request.getReader().lines().collect(java.util.stream.Collectors.joining(System.lineSeparator()));

            ObjectMapper objectMapper = new ObjectMapper();
            VagasModel vaga = objectMapper.readValue(corpoRequisicao, VagasModel.class);

            int vagaId = cadastrarVaga(vaga);

            if (vagaId != -1) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write("Vaga inserida com sucesso");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Erro ao inserir vaga");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao inserir vaga");
        }
    }


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
