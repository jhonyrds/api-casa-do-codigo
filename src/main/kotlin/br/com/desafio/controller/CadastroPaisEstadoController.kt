package br.com.desafio.controller

import br.com.desafio.repository.EstadoRepository
import br.com.desafio.repository.PaisRepository
import br.com.desafio.request.EstadoRequest
import br.com.desafio.request.PaisRequest
import br.com.desafio.response.EstadoResponse
import br.com.desafio.response.PaisResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController()
@RequestMapping("/api")
class CadastroPaisEstadoController(val paisRepository: PaisRepository, val estadoRepository: EstadoRepository) {

    @PostMapping("/pais")
    fun cadastraPais(@RequestBody @Valid request: PaisRequest): ResponseEntity<PaisResponse> {
        val pais = request.toModel()
        paisRepository.save(pais)
        return ResponseEntity.ok(PaisResponse(pais.id!!, pais.nome))
    }

    @PostMapping("/estado")
    fun cadastraEstado(@RequestBody @Valid request: EstadoRequest): ResponseEntity<EstadoResponse> {

        val estado = request.toModel(paisRepository)
        if (estado != null){
            estadoRepository.save(estado)
            return ResponseEntity.ok(EstadoResponse(estado.id!!, estado.nome))
        }
        return ResponseEntity.badRequest().build()
    }

}