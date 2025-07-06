package med.net.api.domain.Consulta.Validaciones;

import med.net.api.domain.Consulta.ConsultaRepository;
import med.net.api.domain.Consulta.DatosReservaConsulta;
import med.net.api.domain.Medico.MedicoRepository;
import med.net.api.domain.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ValidadorMedicoConOtraConsultaEnMismoHorario implements ValidadorDeConsultas{
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosReservaConsulta datos){
        var medicoTieneOtraConsultaEnMismoHorario = consultaRepository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());
        if(medicoTieneOtraConsultaEnMismoHorario){
            throw new ValidacionException("El medico asignado ya tiene otra consulta en el horario seleccionado");
        }


    }
}
