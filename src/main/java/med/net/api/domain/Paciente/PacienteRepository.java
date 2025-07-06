package med.net.api.domain.Paciente;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findByActivoTrue(Pageable pageable);

    @Query(value = """
            SELECT p.activo FROM paciente p WHERE p.id = :idPaciente
            """)
    boolean findActivoById(@NotNull Long idPaciente);

}
