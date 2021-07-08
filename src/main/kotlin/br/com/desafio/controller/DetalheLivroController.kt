package br.com.desafio.controller

import br.com.desafio.modelo.Livro
import br.com.desafio.repository.LivroRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.*

@RestController
@RequestMapping("/api/detalhes")
class DetalheLivroController(val livroRepository: LivroRepository) {

    @GetMapping("/{id}")
    fun detalhar(@PathVariable id: Long): ResponseEntity<DetalheResponse> {
        val livro: Optional<Livro> = livroRepository.findById(id)
        var livroDetalhes: DetalheResponse? = null
        if (livro.isPresent) {
            livroDetalhes = DetalheResponse(
                titulo = livro.get().titulo,
                resumo = livro.get().resumo,
                sumario = livro.get().sumario,
                categoria = livro.get().categoria.categoria,
                autor = livro.get().autor.nome,
                descricaoAutor = livro.get().autor.descricao,
                preco = livro.get().preco,
                paginas = livro.get().paginas,
                isbn = livro.get().isbn,
                dataPublicacao = livro.get().dataPublicacao
            )
            return ResponseEntity.ok(livroDetalhes)
        }
        return ResponseEntity.notFound().build()
    }

    data class DetalheResponse(
        val titulo: String,
        val resumo: String,
        val sumario: String,
        val categoria: String,
        val autor: String,
        val descricaoAutor: String,
        val preco: BigDecimal,
        val paginas: Int,
        val isbn: String,
        val dataPublicacao: String
    )
}