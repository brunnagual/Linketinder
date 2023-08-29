package teste

import org.junit.Test
import org.mockito.Mockito

import Users.Candidato

class CandidatoTest {

    @Test
    void testCriarCandidato() {
        // Arrange (preparação)
        def competencias = ["Java", "Python"]
        def mockCandidato = Mockito.mock(Candidato.class)

        // Comportamento esperado para os métodos getter do mock
        Mockito.when(mockCandidato.getNome()).thenReturn("Candidato Teste")
        Mockito.when(mockCandidato.getEmail()).thenReturn("teste@example.com")
        Mockito.when(mockCandidato.getIdade()).thenReturn(28)
        Mockito.when(mockCandidato.getEstado()).thenReturn("Estado Teste")
        Mockito.when(mockCandidato.getCep()).thenReturn(12345)
        Mockito.when(mockCandidato.getCpf()).thenReturn(123456789)
        Mockito.when(mockCandidato.getDescricaoPessoal()).thenReturn("Descrição Teste")
        Mockito.when(mockCandidato.getCompetencias()).thenReturn(competencias)

        // Act
        def nome = mockCandidato.getNome()
        def email = mockCandidato.getEmail()
        def idade = mockCandidato.getIdade()
        def estado = mockCandidato.getEstado()
        def cep = mockCandidato.getCep()
        def cpf = mockCandidato.getCpf()
        def descricaoPessoal = mockCandidato.getDescricaoPessoal()
        def competenciasObtidas = mockCandidato.getCompetencias()

        // Assert (verificação)
        assert nome == "Candidato Teste"
        assert email == "teste@example.com"
        assert idade == 28
        assert estado == "Estado Teste"
        assert cep == 12345
        assert cpf == 123456789
        assert descricaoPessoal == "Descrição Teste"
        assert competenciasObtidas == competencias
    }
}


