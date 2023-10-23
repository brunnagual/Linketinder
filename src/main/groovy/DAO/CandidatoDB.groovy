package DAO

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

import Model.Candidato

class CandidatoDB{

    private static final String SQL_INSERIR_CANDIDATO = "INSERT INTO public.candidatos(nome, sobrenome, email, cep, cpf, pais, descricao, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"

    static void listarCandidatos() {
        Connection con = new Conexao().getConnection()
        String sql = "SELECT * FROM candidatos"
        ResultSet conjuntoResultados = null
        try {
            PreparedStatement stmt = con.prepareStatement(sql)
            conjuntoResultados = stmt.executeQuery()

            while (conjuntoResultados.next()) {
                exibirInformacoesCandidato(conjuntoResultados)
            }
        } catch (SQLException e) {
            handleSQLException(e)
        } finally {
            fecharConjuntoResultados(conjuntoResultados)
        }
        Conexao.desconectar (con)
    }

    static int inserirCandidatoNoBanco(String nome, String sobrenome, String email, String cep, long cpf, String descricaoPessoal) {
        Connection con = new Conexao().getConnection()
        try {
            PreparedStatement stmtCandidato = con.prepareStatement(SQL_INSERIR_CANDIDATO, Statement.RETURN_GENERATED_KEYS)
            stmtCandidato.setString(1, nome)
            stmtCandidato.setString(2, sobrenome)
            stmtCandidato.setString(3, email)
            stmtCandidato.setString(4, cep)
            stmtCandidato.setLong(5, cpf)
            stmtCandidato.setString(6, "Brasil")
            stmtCandidato.setString(7, descricaoPessoal)
            stmtCandidato.setString(8, "senha_padrao")

            int rowsAffected = stmtCandidato.executeUpdate()
            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmtCandidato.getGeneratedKeys()
                int candidatoId = -1

                if (generatedKeys.next()) {
                    candidatoId = generatedKeys.getInt(1)
                }

                return candidatoId
            }
        } catch (SQLException e) {
            handleSQLException(e)
        }
        return -1
        Conexao.desconectar (con)
    }

    static void exibirInformacoesCandidato(ResultSet conjuntoResultados) {
        try {
            String nome = conjuntoResultados.getString("nome")
            String sobrenome = conjuntoResultados.getString("sobrenome")
            String email = conjuntoResultados.getString("email")
            String cep = conjuntoResultados.getString("cep")
            String descricaoPessoal = conjuntoResultados.getString("descricao")

            println("$nome | $sobrenome | $email | $cep | $descricaoPessoal")
        } catch (SQLException e) {
            handleSQLException(e)
        }
    }

    static void handleSQLException(SQLException e) {
        e.printStackTrace()
    }

    static void fecharConjuntoResultados(ResultSet conjuntoResultados) {
        if (conjuntoResultados != null) {
            try {
                conjuntoResultados.close()
            } catch (SQLException e) {
                handleSQLException(e)
            }
        }
    }

    static void cadastrarCandidato(Candidato candidato, Scanner scanner) {
        Connection con = new Conexao().getConnection()

        String nome = candidato.nome
        String sobrenome = candidato.sobrenome
        String email = candidato.email
        String cep = candidato.cep
        long cpf = candidato.cpf
        String descricaoPessoal = candidato.descricaoPessoal

        int candidatoId = inserirCandidatoNoBanco(nome, sobrenome, email, cep, cpf, descricaoPessoal)

        if (candidatoId != -1) {
            listarTodasCompetencias()

            while (associarCompetencia(candidatoId,scanner)) {
            }

            mostrarSucesso("Candidato")
        } else {
            println("Falha ao cadastrar o candidato.")
        }
        Conexao.desconectar (con)
    }

    static void listarTodasCompetencias() {
        Connection con = new Conexao().getConnection()
        try {
            DatabaseUtil.listarTodasCompetencias(con)
        } finally {
            Conexao.desconectar(con)
        }
    }

    static boolean associarCompetencia(int candidatoId, Scanner scanner) {
        Connection con = new Conexao().getConnection()

        int idCompetencia = capturarEntradaInt("Digite o número da competência que deseja associar: ", scanner)

        String sqlVerificarCompetencia = "SELECT * FROM competencias WHERE id = ?"
        try {
            PreparedStatement stmtVerificarCompetencia = con.prepareStatement(sqlVerificarCompetencia)
            stmtVerificarCompetencia.setInt(1, idCompetencia)
            ResultSet resultado = stmtVerificarCompetencia.executeQuery()

            if (resultado.next()) {
                String sqlAssociacao = "INSERT INTO public.candidatos_competencias(id_candidatos, id_competencias) VALUES (?, ?);"
                PreparedStatement stmtAssociacao = con.prepareStatement(sqlAssociacao)
                stmtAssociacao.setInt(1, candidatoId)
                stmtAssociacao.setInt(2, idCompetencia)
                stmtAssociacao.executeUpdate()

                println("Competência cadastrada com sucesso")
            } else {
                println("Competência não encontrada. Tente novamente.")
            }
        } catch (SQLException e) {
            e.printStackTrace()
        }

        println("Deseja associar outra competência? (S/N): ")
        String resposta = scanner.next().toUpperCase()
        return resposta.equals("S")

        Conexao.desconectar (con)
    }

    static int capturarEntradaInt(String mensagem, Scanner scanner) {
        print(mensagem)
        return scanner.nextInt()
    }

    static void mostrarSucesso(String tipo) {
        println("$tipo cadastrado com sucesso.")
    }
}