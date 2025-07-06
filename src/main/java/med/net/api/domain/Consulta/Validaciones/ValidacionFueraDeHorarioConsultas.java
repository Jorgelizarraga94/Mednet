package med.net.api.domain.Consulta.Validaciones;
import med.net.api.domain.Consulta.DatosReservaConsulta;
import med.net.api.domain.ValidacionException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class ValidacionFueraDeHorarioConsultas implements ValidadorDeConsultas {
    public void validar(DatosReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesAperturaClinica = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierre = fechaConsulta.getHour() > 18;
        if(domingo || horarioDespuesDeCierre || horarioDespuesDeCierre){
            throw new ValidacionException("Horario seleccionado fuera de atenciòn");
        }
    }

}
