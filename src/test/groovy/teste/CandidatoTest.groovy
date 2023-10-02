package teste

import Users.Candidato
import org.junit.Before
import org.junit.jupiter.api.Test

//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mockito

class CandidatoTest {

    def competencias = ["Java", "Python"]

    @Before
    void setup() {
        competencias = ["Java", "Python"]
    }

    @Test
    void testNomeCandidato() {
        // Arrange
        def mockCandidato = Mockito.mock(Candidato.class)
        def nomeEsperado ="Candidato Teste"
        Mockito.when(mockCandidato.getNome()).thenReturn(nomeEsperado)

        // Act
        def nomeObtido = mockCandidato.getNome()

        //Assert
        assert nomeObtido == nomeEsperado

    }

    @Test
    void testEmailCandidato() {
        // Arrange
        def mockCandidato = Mockito.mock(Candidato.class)
        def emailEsperado ="teste@example.com"
        Mockito.when(mockCandidato.getEmail()).thenReturn(emailEsperado)

        // Act
        def emailObtido = mockCandidato.getEmail()

        //Assert
        assert emailObtido == emailEsperado
    }

    @Test
    void testIdadeCandidato() {
        // Arrange
        def mockCandidato = Mockito.mock(Candidato.class)
        def idadeEsperado =30
        Mockito.when(mockCandidato.getIdade()).thenReturn(idadeEsperado)

        // Act
        def idadeObtido = mockCandidato.getIdade()

        //Assert
        assert idadeObtido == idadeEsperado
    }

    @Test
    void testEstadoCandidato() {
        // Arrange
        def mockCandidato = Mockito.mock(Candidato.class)
        def estadoEsperado ="GO"
        Mockito.when(mockCandidato.getEstado()).thenReturn(estadoEsperado)

        // Act
        def estadoObtido = mockCandidato.getEstado()

        //Assert
        assert estadoObtido == estadoEsperado
    }

    @Test
    void testCepCandidato() {
        // Arrange
        def mockCandidato = Mockito.mock(Candidato.class)
        def cepEsperado = 12345
        Mockito.when(mockCandidato.getCep()).thenReturn(cepEsperado)

        // Act
        def cepObtido = mockCandidato.getCep()

        //Assert
        assert cepObtido == cepEsperado
    }

    @Test
    void testCpfCandidato() {
        // Arrange
        def mockCandidato = Mockito.mock(Candidato.class)
        def cpfEsperado = 123456789
        Mockito.when(mockCandidato.getCpf()).thenReturn(cpfEsperado)

        // Act
        def cpfObtido = mockCandidato.getCpf()

        //Assert
        assert cpfObtido == cpfEsperado
    }

    @Test
    void testDescricaoCandidato() {
        // Arrange
        def mockCandidato = Mockito.mock(Candidato.class)
        def descricaoEsperado = "Descrição Teste"
        Mockito.when(mockCandidato.getDescricaoPessoal()).thenReturn(descricaoEsperado)

        // Act
        def descricaoObtido = mockCandidato.getDescricaoPessoal()

        //Assert
        assert descricaoObtido == descricaoEsperado
    }

    @Test
    void testCompetenciasCandidato() {
        // Arrange
        def mockCandidato = Mockito.mock(Candidato.class)
        def competenciasEsperado = competencias
        Mockito.when(mockCandidato.getCompetencias()).thenReturn(competenciasEsperado)

        // Act
        def competenciasObtido = mockCandidato.getCompetencias()

        //Assert
        assert competenciasObtido == competenciasEsperado
    }

}


