package br.com.desafio.request

import br.com.desafio.modelo.Pais
import javax.validation.constraints.NotBlank

data class PaisRequest(
    @field:NotBlank val nome: String
) {
    fun toModel(): Pais {
        return Pais(nome)
    }
}
