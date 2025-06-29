package med.net.api.Paciente;

import med.net.api.Medico.Especialidad;

public record DatosListaPaciente(
        String nombre,
        String email,
        String documento
) {
    public DatosListaPaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumento_identidad());
    }
}
