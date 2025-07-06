package med.net.api.domain.Consulta;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.net.api.domain.Medico.Medico;
import med.net.api.domain.Paciente.Paciente;

import java.time.LocalDateTime;

@Table(name="consultas")
@Entity(name="consulta")
@Getter
@EqualsAndHashCode(of = "id") //reconoce si los objetos son iguales si coincide el id
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private LocalDateTime fecha;

    @Column(name = "motivo_cancelamiento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamiento motivoCancelamiento;

    public Consulta(Long id, Medico medico, Paciente paciente, LocalDateTime fecha, MotivoCancelamiento motivoCancelamiento) {
        this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.motivoCancelamiento = motivoCancelamiento;
    }

    public void cancelar(MotivoCancelamiento motivo) {
        this.motivoCancelamiento = motivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public MotivoCancelamiento getMotivoCancelamiento() {
        return motivoCancelamiento;
    }

    public void setMotivoCancelamiento(MotivoCancelamiento motivoCancelamiento) {
        this.motivoCancelamiento = motivoCancelamiento;
    }
}
