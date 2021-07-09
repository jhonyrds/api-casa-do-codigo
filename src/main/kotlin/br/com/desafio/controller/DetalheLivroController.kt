package br.com.desafio.controller

import br.com.desafio.modelo.Livro
import br.com.desafio.repository.LivroRepository
import br.com.desafio.response.DetalheLivroReponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/detalhes")
class DetalheLivroController(val livroRepository: LivroRepository) {

    @GetMapping("/{id}")
    fun detalhar(@PathVariable id: Long): ResponseEntity<DetalheLivroReponse> {
        val livro: Optional<Livro> = livroRepository.findById(id)
        if (livro.isEmpty) {
            return ResponseEntity.notFound().build()
        }
        val possivelLivro = livro.get()

        return ResponseEntity.ok().body(DetalheLivroReponse(possivelLivro))
    }
}