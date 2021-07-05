package br.com.desafio.repository

import br.com.desafio.modelo.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoriaRepository : JpaRepository<Categoria, Long>{
    abstract fun findByCategoria(categoria: String): Optional<Categoria>
}