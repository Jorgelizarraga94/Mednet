package med.net.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.net.api.domain.Medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datos){
        medicoRepository.save(new Medico(datos));
    }

    //findByActivoTrue no es un metodo de JPA, sin embargo JPA interpreta lo que queremos hacer y crea el metodo dentro del controller.
    @GetMapping
    public ResponseEntity<Page<DatosListaMedico>> listar(@PageableDefault(size=10, sort = {"nombre"}) Pageable paginacion){
        var page = medicoRepository.findByActivoTrue(paginacion).map(DatosListaMedico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }

    @Transactional //En este caso no es necesario hacer un save para guardar en la DB gracias a @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
        Medico medico = medicoRepository.getReferenceById(datos.id());
        medico.actualizarInformacion(datos);

        return ResponseEntity.ok(new DatosDetalleMedico(medico));
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity EliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.eliminar();

        return ResponseEntity.noContent().build(); //devuelve el codigo 204 luego de la eliminaciòn
    }
}
