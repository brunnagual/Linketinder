package Controller

import DAO.ConexaoDAO
import DAO.CandidatoDAO
import Model.CandidatoModel

import java.sql.Connection

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import com.google.gson.Gson

@WebServlet("/candidato")
class CandidatoController extends HttpServlet{
    private static final long serialVersionUID = 1L
    private Gson gson = new Gson()

    CandidatoDAO candidatoDao = new CandidatoDAO()

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<CandidatoModel> candidatos = candidatoDao.listarCandidatos()

            String jsonCandidatos = convertToJSON(candidatos);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonCandidatos);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao obter a lista de candidatos.");
        }
    }

    private static String convertToJSON(List<CandidatoModel> candidatos) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(candidatos);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String corpoRequisicao = request.getReader().lines().collect(java.util.stream.Collectors.joining(System.lineSeparator()));

            ObjectMapper objectMapper = new ObjectMapper();
            CandidatoModel candidato = objectMapper.readValue(corpoRequisicao, CandidatoModel.class);

            boolean sucesso = cadastrarCandidato(candidato);

            if (sucesso) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write("Candidato inserido com sucesso");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Erro ao inserir candidato");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao inserir candidato");
        }
    }


    static boolean cadastrarCandidato(CandidatoModel candidato, Scanner scanner) {
        Connection con = ConexaoDAO.getInstance().getConnection()

            int candidatoId = CandidatoDAO.inserirCandidatoNoBanco(candidato.nome, candidato.sobrenome, candidato.email, candidato.cep, candidato.cpf, candidato.descricaoPessoal)

            if (candidatoId != -1) {
                CandidatoDAO.listarTodasCompetencias()

                boolean continuar = true

                while (continuar) {

                    int idCompetencia = RegexController.capturarEntradaInt("Digite o número da competência que deseja associar: ", scanner)
                    CandidatoDAO.associarCompetencia(candidatoId, idCompetencia)
                    println("Deseja associar outra competência? (S/N): ")
                    String resposta = scanner.next().toUpperCase()

                    continuar = resposta.equals("S")
                }
                    return true
            } else {
                return false
            }
        }

    static List<CandidatoModel> listarCandidatos(){
        return CandidatoDAO.listarCandidatos()
    }
}