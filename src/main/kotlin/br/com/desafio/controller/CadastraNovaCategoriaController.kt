package br.com.desafio.controller

import br.com.desafio.exception.CadastroException
import br.com.desafio.modelo.Categoria
import br.com.desafio.repository.CategoriaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("api/categorias")
class CadastraNovaCategoriaController(val categoriaRepository: CategoriaRepository) {

    @PostMapping
    fun cadastra(@RequestBody @Valid novaCategoria: NovaCategoriaRequest):ResponseEntity<Any> {
        val existeCategoria = categoriaRepository.findByCategoria(novaCategoria.categoria)
        if (existeCategoria.isEmpty) {
            val categoria: Categoria = novaCategoria.toModel()
            categoriaRepository.save(categoria)
            val response = NovaCategoriaResponse(categoria.id!!, categoria.categoria)
            return ResponseEntity.ok(response)
        }
        throw CadastroException(mensagem = "A categoria ${novaCategoria.categoria} já foi cadastrada")
    }

    data class NovaCategoriaRequest(
        @field:NotBlank val categoria: String
    ){
        fun toModel() = Categoria(
            categoria = categoria
        )
    }

    data class NovaCategoriaResponse(
        val id: Long,
        val categoria: String
    )

}