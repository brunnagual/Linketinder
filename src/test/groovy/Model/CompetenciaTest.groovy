package Model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CompetenciaTest {
    private List<CompetenciaModel> competencias;

    @BeforeEach
    void setUp() {
        competencias = new ArrayList<>();
    }

    @Test
    void testAdicionarEmpresa() {
        CompetenciaModel competencia = new CompetenciaModel("Java");
        competencias.add(competencia);

        Assertions.assertEquals("Java", competencias.get(0).nomeCompetencias);
    }
}
