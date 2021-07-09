package br.com.desafio.response

import br.com.desafio.modelo.Livro

class DetalheLivroReponse(livro: Livro) {
    val titulo = livro.titulo
    val resumo = livro.resumo
    val sumario = livro.sumario
    val categoria = livro.categoria.categoria
    val autor = livro.autor.nome
    val descricaoAutor = livro.autor.descricao
    val preco = livro.preco
    val paginas = livro.paginas
    val isbn = livro.isbn
    val dataPublicacao = livro.dataPublicacao
}

