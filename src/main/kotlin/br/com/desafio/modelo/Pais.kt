package br.com.desafio.modelo

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
class Pais(@field:NotBlank @field:Column(unique = true, nullable = false) val nome: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
