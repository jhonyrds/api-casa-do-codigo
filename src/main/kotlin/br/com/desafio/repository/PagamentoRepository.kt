package br.com.desafio.repository

import br.com.desafio.modelo.Pagamento
import org.springframework.data.jpa.repository.JpaRepository

interface PagamentoRepository: JpaRepository<Pagamento, Long>
