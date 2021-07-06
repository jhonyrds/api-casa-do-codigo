package br.com.desafio.repository

import br.com.desafio.modelo.Livro
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LivroRepository: JpaRepository<Livro, Long> {
    abstract fun findByTitulo(titulo: String) : Optional<Livro>
}