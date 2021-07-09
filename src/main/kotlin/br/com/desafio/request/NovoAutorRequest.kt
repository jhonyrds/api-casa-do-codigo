package br.com.desafio.request

import br.com.desafio.modelo.Autor
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

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