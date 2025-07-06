package med.net.api.domain.Consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndFecha(Long idMedico, @NotNull @Future LocalDateTime fecha);

    boolean existsByPacienteIdAndFechaBetween(@NotNull Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);
}
