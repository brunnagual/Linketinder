package Model

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EmpresaTest {

    private List<EmpresaModel> empresas;

    @BeforeEach
    void setUp() {
        empresas = new ArrayList<>();
    }

    @Test
    void testAdicionarEmpresa() {
        EmpresaModel empresa = new EmpresaModel(-1,"Empresa", "joao@example.com", "12345678", "12345759000105", "Cadastrando empresa");
        empresas.add(empresa);

        Assertions.assertEquals("Empresa", empresas.get(0).nome);
        Assertions.assertEquals("joao@example.com", empresas.get(0).email);
        Assertions.assertEquals("12345678", empresas.get(0).cep);
        Assertions.assertEquals("12345759000105", empresas.get(0).cnpj);
        Assertions.assertEquals("Cadastrando empresa", empresas.get(0).descricao);
    }
}