package br.com.desafio.controller

import br.com.desafio.modelo.Autor
import br.com.desafio.repository.AutorRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/autores")
class CadastraNovoAutorController(val autorRepository: AutorRepository) {

    @PostMapping
    fun cadastra(@RequestBody @Valid novoAutor: NovoAutorRequest): ResponseEntity<Any> {
        val autor: Autor = novoAutor.toModel()
        autorRepository.save(autor)
        return ResponseEntity.ok().build()
    }

    data class NovoAutorRequest(
        @field:NotBlank val nome: String,
        @field:NotBlank @field:Email val email: String,
        @field:NotBlank @field:Size(max = 400) val descricao: String
    ) {
        fun toModel() = Autor(
            nome = nome,
            email = email,
            descricao = descricao
        )
    }

}
