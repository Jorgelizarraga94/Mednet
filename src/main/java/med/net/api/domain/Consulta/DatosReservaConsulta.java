package med.net.api.domain.Consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.net.api.domain.Medico.Especialidad;

import java.time.LocalDateTime;

public record DatosReservaConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime fecha,
        Especialidad especialidad
) {
}
