package br.com.desafio.modelo

import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.*

@Entity
class Livro(
    @field:NotBlank val titulo: String,
    @field:NotBlank @field:Size(max = 500) val resumo: String,
    val sumario: String,
    @field:NotNull @field:Positive val preco: BigDecimal,
    @field:NotNull @field:Min(100) val paginas: Int,
    @field:NotBlank val isbn: String,
    @field:NotBlank val dataPublicacao: String,
    @field:NotNull @field:ManyToOne val categoria: Categoria,
    @field:NotNull @field:ManyToOne val autor: Autor
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}