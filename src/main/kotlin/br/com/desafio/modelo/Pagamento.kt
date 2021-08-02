package br.com.desafio.modelo

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Pagamento(
    @field:Email @field:Column(unique = true, nullable = false) val email: String,
    @field:NotBlank val nome: String,
    @field:NotBlank val sobrenome: String,
    @field:NotBlank @field:Column(unique = true, nullable = false) val documento: String,
    @field:NotBlank val endereco: String,
    @field:NotBlank val complemento: String,
    @field:NotBlank val cidade: String,
    @field:NotNull @field:ManyToOne val pais: Pais,
    @field:ManyToOne val estado: Estado,
    @field:NotBlank val telefone: String,
    @field:NotBlank val cep: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
