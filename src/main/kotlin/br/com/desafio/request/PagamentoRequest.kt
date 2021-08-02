package br.com.desafio.request

import br.com.desafio.modelo.Pagamento
import br.com.desafio.repository.EstadoRepository
import br.com.desafio.repository.PaisRepository
import br.com.desafio.util.CpfOuCnpj
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class PagamentoRequest(
    @field:Email val email: String,
    @field:NotBlank val nome: String,
    @field:NotBlank val sobrenome: String,
    @field:NotBlank @field:CpfOuCnpj val documento: String,
    @field:NotBlank val endereco: String,
    @field:NotBlank val complemento: String,
    @field:NotBlank val cidade: String,
    @field:NotNull val paisId: Long,
    val estadoId: Long,
    @field:NotBlank val telefone: String,
    @field:NotBlank val cep: String
) {
    fun toModel(paisRepository: PaisRepository, estadoRepository: EstadoRepository): Pagamento? {
        val possivelEstado = estadoRepository.findById(estadoId)
        val possivelPais = paisRepository.findById(paisId)

        if (possivelEstado.isPresent && possivelPais.isPresent) {
            return Pagamento(
                email = email,
                nome = nome,
                sobrenome = sobrenome,
                documento = documento,
                endereco = endereco,
                complemento = complemento,
                cidade = cidade,
                pais = possivelPais.get(),
                estado = possivelEstado.get(),
                telefone = telefone,
                cep = cep
            )
        }
        return null
    }

}
