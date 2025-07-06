package med.net.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.net.api.domain.Consulta.DatosCancelamientoConsulta;
import med.net.api.domain.Consulta.DatosDetalleConsulta;
import med.net.api.domain.Consulta.DatosReservaConsulta;
import med.net.api.domain.Consulta.ReservaDeConsultas;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {
    @Autowired
    private ReservaDeConsultas reserva;
    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos){

        var detalleConsulta = reserva.reservar(datos);
        return ResponseEntity.ok(detalleConsulta);
    }
    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        reserva.cancelar(datos);
        return ResponseEntity.noContent().build();
    }
}
