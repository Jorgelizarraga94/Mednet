package med.net.api.controller;

import jakarta.validation.Valid;
import med.net.api.Medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
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
    public Page<DatosListaMedico> listar(@PageableDefault(size=10, sort = {"nombre"}) Pageable paginacion){
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListaMedico::new);
    }
    @Transactional //En este caso no es necesario hacer un save para guardar en la DB gracias a @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionMedico datos){
        Medico medico = medicoRepository.getReferenceById(datos.id());
        medico.actualizarInformacion(datos);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public void EliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.eliminar();
    }
}
