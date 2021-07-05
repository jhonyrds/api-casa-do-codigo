package br.com.desafio.exception

class CadastroException(
    mensagem: String = "Falha ao realizar cadastro"
) : Exception(mensagem)