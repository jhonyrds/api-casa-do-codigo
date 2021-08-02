package br.com.desafio.controller

import br.com.desafio.repository.EstadoRepository
import br.com.desafio.repository.PagamentoRepository
import br.com.desafio.repository.PaisRepository
import br.com.desafio.request.PagamentoRequest
import br.com.desafio.response.PagamentoReponse
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/pagamento")
class FluxoDePagamentoController(
    val repository: PagamentoRepository,
    val estadoRepository: EstadoRepository,
    val paisRepository: PaisRepository,
) {

    val LOGGER = LoggerFactory.getLogger(FluxoDePagamentoController::class.java)

    @PostMapping
    fun processa(@RequestBody @Valid request: PagamentoRequest): ResponseEntity<PagamentoReponse> {

        val pagamento = request.toModel(paisRepository, estadoRepository) ?: return ResponseEntity.badRequest().build()

        LOGGER.info("Processando novo pagamento: $request")

        repository.save(pagamento)

        return ResponseEntity.ok().body(PagamentoReponse(pagamento))
    }
}