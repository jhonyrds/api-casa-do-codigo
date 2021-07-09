package br.com.desafio.response

import br.com.desafio.modelo.Livro

class ListaLivrosResponse(livro: Livro) {
    val id = livro.id
    val nome = livro.titulo
}
