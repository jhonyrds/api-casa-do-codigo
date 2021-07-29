package br.com.desafio.modelo

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
class Estado(
    @field:NotBlank @field:Column(unique = true, nullable = false) val nome: String,
    @field:NotNull @field:ManyToOne val pais: Pais
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
