package Model

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CandidatoModelTest {

    private List<CandidatoModel> candidatos;

    @BeforeEach
    void setUp() {
        candidatos = new ArrayList<>();
    }

    @Test
    void testAdicionarCandidato() {
        CandidatoModel candidato = new CandidatoModel("João", "Martins", "joao@example.com", "12345678", 12345678901L, "Descrição Pessoal", new ArrayList<>());
        candidatos.add(candidato);

        Assertions.assertEquals("João", candidatos.get(0).nome);
        Assertions.assertEquals("Martins", candidatos.get(0).sobrenome);
        Assertions.assertEquals("joao@example.com", candidatos.get(0).email);
        Assertions.assertEquals(12345678901L, candidatos.get(0).cpf);
        Assertions.assertEquals("12345678", candidatos.get(0).cep);
        Assertions.assertEquals("Descrição Pessoal", candidatos.get(0).descricaoPessoal);
    }
}
