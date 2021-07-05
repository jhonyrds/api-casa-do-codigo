package br.com.desafio.controller

import br.com.desafio.exception.CadastroException
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
        val existecadastro = autorRepository.findByEmail(novoAutor.email)
        if (existecadastro.isEmpty) {
            val autor: Autor = novoAutor.toModel()
            autorRepository.save(autor)
            val response = NovoAutorResponse(autor.id!!,autor.nome)
            return ResponseEntity.ok(response)
        }
        throw CadastroException(
            mensagem = "Usuário ${novoAutor.email} já está cadastrado"
        )
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

    data class NovoAutorResponse(
        val id: Long,
        val nome: String,
    )

}
