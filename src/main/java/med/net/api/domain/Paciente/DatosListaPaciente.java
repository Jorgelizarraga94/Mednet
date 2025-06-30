package med.net.api.domain.Paciente;

public record DatosListaPaciente(
        String nombre,
        String email,
        String documento
) {
    public DatosListaPaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumento_identidad());
    }
}
