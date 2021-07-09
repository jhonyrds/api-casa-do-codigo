package br.com.desafio.request

import br.com.desafio.modelo.Livro
import br.com.desafio.repository.AutorRepository
import br.com.desafio.repository.CategoriaRepository
import java.math.BigDecimal
import javax.validation.constraints.*

data class NovoLivroRequest(
    @field:NotBlank val titulo: String,
    @field:NotBlank @field:Size(max = 500) val resumo: String,
    val sumario: String,
    @field:NotNull @field:Positive val preco: BigDecimal,
    @field:NotNull @field:Min(100) val paginas: Int,
    @field:NotBlank val isbn: String,
    @field:NotBlank val dataPublicacao: String,
    @field:NotNull val categoria: Long,
    @field:NotNull val autor: Long
) {
    fun toLivro(
        categoriaRepository: CategoriaRepository,
        autorRepository: AutorRepository
    ): Livro {
        val categoriaId = categoriaRepository.findById(categoria)
        val autorId = autorRepository.findById(autor)
        if (categoriaId.isPresent && autorId.isPresent) {
            return Livro(
                titulo = titulo,
                resumo = resumo,
                sumario = sumario,
                preco = preco,
                paginas = paginas,
                isbn = isbn,
                dataPublicacao = dataPublicacao,
                categoria = categoriaRepository.findById(categoria).get(),
                autor = autorRepository.findById(autor).get()
            )
        }
        return throw Exception("Autor ou Categoria inválidos")
    }

}