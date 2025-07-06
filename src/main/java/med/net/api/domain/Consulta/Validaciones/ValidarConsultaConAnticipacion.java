package med.net.api.domain.Consulta.Validaciones;

import med.net.api.domain.Consulta.DatosReservaConsulta;
import med.net.api.domain.ValidacionException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
@Service
public class ValidarConsultaConAnticipacion implements ValidadorDeConsultas{
    public void validar(DatosReservaConsulta datos){
        var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora,fechaConsulta).toMinutes();

        if(diferenciaEnMinutos < 30){
            throw new ValidacionException("horario seleccionado con menos de 30 minutos de anticipacion");
        }
    }
}
