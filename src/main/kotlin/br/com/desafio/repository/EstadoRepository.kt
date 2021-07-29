package br.com.desafio.repository

import br.com.desafio.modelo.Estado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EstadoRepository: JpaRepository<Estado, Long> {
    fun findByNome(nome: String): Estado
}
