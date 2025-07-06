package med.net.api.domain.Consulta.Validaciones;

import med.net.api.domain.Consulta.DatosReservaConsulta;
import med.net.api.domain.Medico.MedicoRepository;
import med.net.api.domain.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ValidadorMedicoActivo implements ValidadorDeConsultas{
    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosReservaConsulta datos) {
        if(datos.idMedico() == null){
            return;
        }
        var medicoActivo = medicoRepository.findActivoById(datos.idMedico());
        if(!medicoActivo){
            throw new ValidacionException("Medico no activo");
        }
    }
}
