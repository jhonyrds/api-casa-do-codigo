package br.com.desafio.controller

import br.com.desafio.exception.CadastroException
import br.com.desafio.modelo.Livro
import br.com.desafio.repository.AutorRepository
import br.com.desafio.repository.CategoriaRepository
import br.com.desafio.repository.LivroRepository
import br.com.desafio.request.NovoLivroRequest
import br.com.desafio.response.ListaLivrosResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/livros")
class CadastroNovoLivroController(
    val livroRepository: LivroRepository,
    val autorRepository: AutorRepository,
    val categoriaRepository: CategoriaRepository
) {

    @PostMapping
    fun cadastra(@RequestBody @Valid novoLivro: NovoLivroRequest): ResponseEntity<Any> {
        var existeTitulo = livroRepository.findByTitulo(novoLivro.titulo)
        if (existeTitulo.isEmpty) {
            val livro = novoLivro.toLivro(categoriaRepository, autorRepository)
            livroRepository.save(livro)
        }
        throw CadastroException("O titulo ${novoLivro.titulo} já foi cadastrado")
    }
}


