package View

import Controller.EmpresaController
import Controller.RegexController
import Model.EmpresaModel
import Model.RegexModel

class EmpresaView {


    static void exibirInformacoesEmpresa() {

        List<EmpresaModel> empresas = EmpresaController.listarEmpresas()

        for (EmpresaModel empresa: empresas){
            println("$empresa.id | $empresa.nome | $empresa.email | $empresa.cep | $empresa.cnpj | $empresa.descricao")
        }
    }

    static void cadastrarEmpresa(Scanner scanner) {

        String nome = RegexController.validarEntrada("Nome: ", RegexModel.regexNome, scanner)
        String email = RegexController.validarEntrada( "Email: ",RegexModel.regexEmail, scanner)
        String cep = RegexController.validarEntrada( "CEP: ",RegexModel.regexCep, scanner)
        String cnpj = RegexController.validarEntrada( "CNPJ: ",RegexModel.regexCnpj, scanner)
        String descricao = RegexController.validarEntrada( "Descrição: ",RegexModel.regexDrescricao, scanner);

        EmpresaModel empresa = new EmpresaModel(-1,nome, email, cep, cnpj, descricao)

        if (EmpresaController.cadastrarEmpresa(empresa, scanner)){
            println("Okay")
        }else
            println("Not okay")

    }
}
