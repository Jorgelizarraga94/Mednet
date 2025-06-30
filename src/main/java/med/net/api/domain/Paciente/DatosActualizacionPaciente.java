package med.net.api.domain.Paciente;

import med.net.api.domain.Direccion.DatosDireccion;

public record DatosActualizacionPaciente(
        Long id,
        String nombre ,
        String telefono ,
        DatosDireccion direccion
) {
}
