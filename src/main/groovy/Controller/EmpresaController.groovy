package Controller
import DAO.EmpresaDAO
import Model.EmpresaModel
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

@WebServlet("/empresa")
class EmpresaController extends HttpServlet {
    private static final long serialVersionUID = 1L
    private Gson gson = new Gson()

    EmpresaDAO empresaDao = new EmpresaDAO()

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<EmpresaModel> empresas = empresaDao.listarEmpresas()

            String jsonEmpresas = convertToJSON(empresas);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonEmpresas);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao obter a lista de empresas.");
        }
    }

    private static String convertToJSON(List<EmpresaModel> empresas) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(empresas);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String corpoRequisicao = request.getReader().lines().collect(java.util.stream.Collectors.joining(System.lineSeparator()));

            ObjectMapper objectMapper = new ObjectMapper();
            EmpresaModel empresa = objectMapper.readValue(corpoRequisicao, EmpresaModel.class);

            boolean sucesso = cadastrarEmpresa(empresa);

            if (sucesso) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write("Empresa inserida com sucesso");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Erro ao inserir empresa");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Erro ao inserir empresa");
        }
    }

    static boolean cadastrarEmpresa(EmpresaModel empresa, Scanner scanner) {
        Connection con = ConexaoDAO.getInstance().getConnection()

        int empresaId = EmpresaDAO.inserirEmpresaNoBanco(empresa.nome, empresa.email, empresa.cep, empresa.cnpj, empresa.descricao)

    }
    static List<EmpresaModel> listarEmpresas(){
        return EmpresaDAO.listarEmpresas()
    }
}
