package br.com.desafio.controller

import br.com.desafio.exception.CadastroException
import br.com.desafio.modelo.Livro
import br.com.desafio.repository.AutorRepository
import br.com.desafio.repository.CategoriaRepository
import br.com.desafio.repository.LivroRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.*

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
            val livro: Livro = novoLivro.toLivro(categoriaRepository, autorRepository)
            livroRepository.save(livro)
            val response = LivroResponse(livro.id!!, livro.titulo)
            return ResponseEntity.ok(response)
        }
        throw CadastroException("O titulo ${novoLivro.titulo} já foi cadastrado")
    }

    @GetMapping
    fun listaLivros(): ResponseEntity<List<Livro>> {
        val livros: List<Livro> = livroRepository.findAll()
        return ResponseEntity.ok(livros)
    }

    data class NovoLivroRequest(
        @field:NotBlank val titulo: String,
        @field:NotBlank @field:Size(max = 500) val resumo: String,
        val sumario: String,
        @field:NotNull @field:Positive val preco: BigDecimal,
        @field:NotNull @field:Min(100) val paginas: Int,
        @field:NotBlank val isbn: String,
        @field:NotBlank val dataPublicacao: String,
        @field:NotNull val categoria: Long,
        @field:NotNull val autor: Long
    ) {
        fun toLivro(
            categoriaRepository: CategoriaRepository,
            autorRepository: AutorRepository
        ) = Livro(
            titulo = titulo,
            resumo = resumo,
            sumario = sumario,
            preco = preco,
            paginas = paginas,
            isbn = isbn,
            dataPublicacao = dataPublicacao,
            categoria = categoriaRepository.getById(categoria),
            autor = autorRepository.getById(autor)
        )

    }

    data class LivroResponse(
        val id: Long,
        val titulo: String
    )
}


