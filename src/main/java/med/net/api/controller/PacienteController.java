package med.net.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.net.api.Paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datos) {
        repository.save(new Paciente(datos));
    }
    @GetMapping
    public Page<DatosListaPaciente> listarPaciente(@PageableDefault(size=10, sort = "nombre") Pageable pageable){
        return repository.findByActivoTrue(pageable).map(DatosListaPaciente::new);
    }

    @Transactional
    @PutMapping
    public void actualizarPaciente(@RequestBody @Valid DatosActualizacionPaciente datos){
        Paciente paciente = repository.getReferenceById(datos.id());
        paciente.actualizarPaciente(datos);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminarPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.eliminarPaciente();
    }
}
