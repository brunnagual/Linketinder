//package Model
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Test
//
//class EmpresaTest {
//
//    private List<Empresa> empresas;
//
//    @BeforeEach
//    void setUp() {
//        empresas = new ArrayList<>();
//    }
//
//    @Test
//    void testAdicionarEmpresa() {
//        Scanner scanner = new Scanner("EmpresaXYZ\ncontato@empresa.com\n12345678\n12345678901234\nTestando cadastro");
//
//        Empresa.cadastrarEmpresa(empresas, scanner);
//
//        Assertions.assertEquals("EmpresaXYZ", empresas.get(0).nome);
//        Assertions.assertEquals("contato@empresa.com", empresas.get(0).email);
//        Assertions.assertEquals("12345678", empresas.get(0).cep);
//        Assertions.assertEquals("12345678901234", empresas.get(0).cnpj);
//        Assertions.assertEquals("Testando cadastro", empresas.get(0).descricao);
//    }
//}
