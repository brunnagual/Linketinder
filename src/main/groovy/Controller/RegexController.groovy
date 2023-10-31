package Controller

class RegexController {
    static String validarEntrada(String mensagem, String regex, Scanner scanner) {
        String entrada
        boolean entradaValida = false

        while (!entradaValida) {
            print(mensagem)
            entrada = scanner.nextLine().trim()
            if (entrada.matches(regex)) {
                entradaValida = true
            } else {
                println("Entrada inválida. Tente novamente.")
            }
        }

        return entrada
    }

    static long validarEntradaLong(String mensagem, String regex, Scanner scanner) {
        long entrada
        boolean entradaValida = false

        while (!entradaValida) {
            print(mensagem)
            try {
                entrada = Long.parseLong(scanner.nextLine().trim())
                if (String.valueOf(entrada).matches(regex)) {
                    entradaValida = true
                } else {
                    println("Entrada inválida. Tente novamente.")
                }
            } catch (NumberFormatException e) {
                println("Entrada inválida. Deve ser um número válido.")
            }
        }

        return entrada
    }

    static int capturarEntradaInt(String mensagem, Scanner scanner) {
        print(mensagem)
        return scanner.nextInt()
    }

}
