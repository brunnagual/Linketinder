package Users

class Regex {
    static String regexNome = /^[A-Za-zÀ-ÖØ-öø-ÿ\s]{1,35}$/
    static String regexCnpj = /^\d{14}$/
    static String regexEmail = '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$'
    static String regexCep = /^\d{8}$/
    static String regexCpf = /^\d{11}$/
    static String regexDrescricao = /^[A-Za-zÀ-ÖØ-öø-ÿ\s]{1,50}$/
    static String regexSalario = /^\d+(\.\d{1,2})?$/
    static String regexNumero = /^(1?[0-9]|20)$/


    static validarEntrada(regex, String mensagem, Scanner scanner) {
        while (true) {
            print(mensagem)
            String entrada = scanner.nextLine()

            if (entrada.matches(regex)) {
                return entrada
            } else {
                println("Entrada inválida. Por favor, insira uma entrada válida.")
            }
        }
    }
}
