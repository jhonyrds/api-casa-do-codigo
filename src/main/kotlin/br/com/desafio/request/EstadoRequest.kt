package br.com.desafio.request

import br.com.desafio.modelo.Estado
import br.com.desafio.repository.EstadoRepository
import br.com.desafio.repository.PaisRepository
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EstadoRequest(
    @field:NotBlank val nome: String,
    @field:NotNull val idPais: Long
) {

    fun toModel(paisRepository: PaisRepository): Estado? {
        val pais = paisRepository.findById(idPais)
        if (pais.isPresent) {
            return Estado(nome, pais.get())
        }
        return null
    }
}
