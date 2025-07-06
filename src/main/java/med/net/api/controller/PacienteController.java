package med.net.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.net.api.domain.Paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datos) {
        repository.save(new Paciente(datos));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPaciente>> listarPaciente(@PageableDefault(size=10, sort = "nombre") Pageable pageable){
        var page = repository.findByActivoTrue(pageable).map(DatosListaPaciente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPacientePorId(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarPaciente(@RequestBody @Valid DatosActualizacionPaciente datos){
        Paciente paciente = repository.getReferenceById(datos.id());
        paciente.actualizarPaciente(datos);

        return ResponseEntity.ok(new DatosDetallePaciente(paciente));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.eliminarPaciente();

        return ResponseEntity.noContent().build(); //Devuelve un codigo 204 luego de la eliminacion
    }
}
