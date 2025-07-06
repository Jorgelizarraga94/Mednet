package med.net.api.domain.Consulta.Validaciones;

import med.net.api.domain.Consulta.ConsultaRepository;
import med.net.api.domain.Consulta.DatosReservaConsulta;
import med.net.api.domain.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ValidacionPacienteSinOtraConsultaEnElMismoDia implements ValidadorDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosReservaConsulta datos){
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteTieneOtraFechaEnElDia = consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if(pacienteTieneOtraFechaEnElDia){
            throw new ValidacionException("el paciente ya tiene una consulta para el dia " + datos.fecha());
        }

    }
}
