package med.net.api.domain.Consulta.Validaciones;

import med.net.api.domain.Consulta.DatosReservaConsulta;
import med.net.api.domain.Paciente.PacienteRepository;
import med.net.api.domain.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ValidadorPacienteActivo implements ValidadorDeConsultas{
    @Autowired
    private PacienteRepository repository;

    public void validar(DatosReservaConsulta datos){
        var pacienteActivo = repository.findActivoById(datos.idPaciente());
        if(!pacienteActivo){
            throw new ValidacionException("El paciente no se encuentra activo");
        }
    }
}
