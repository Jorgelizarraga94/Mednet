package med.net.api.domain.Paciente;

import med.net.api.domain.Direccion.Direccion;

public record DatosDetallePaciente(
        Long id,
        String nombre,
        String email,
        String documento_identidad,
        String telefono,
        Direccion direccion
) {
    public DatosDetallePaciente(Paciente paciente){
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumento_identidad(),
                paciente.getTelefono(),
                paciente.getDireccion()
        );
    }
}
