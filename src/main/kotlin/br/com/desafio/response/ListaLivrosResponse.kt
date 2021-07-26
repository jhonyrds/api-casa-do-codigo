package br.com.desafio.response

import br.com.desafio.modelo.Livro

data class ListaLivrosResponse(val livro: Livro) {
    val id = livro.id
    val nome = livro.titulo
}
