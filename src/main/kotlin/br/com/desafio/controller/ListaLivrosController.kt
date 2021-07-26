package br.com.desafio.controller

import br.com.desafio.repository.LivroRepository
import br.com.desafio.response.ListaLivrosResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController("/api/livros")
class ListaLivrosController(val livroRepository: LivroRepository) {

    @GetMapping()
    fun lista(): ResponseEntity<List<ListaLivrosResponse>> {
        val livros = livroRepository.findAll()
        val response = livros.map { livro -> ListaLivrosResponse(livro) }
        return ResponseEntity.ok(response)
    }
}