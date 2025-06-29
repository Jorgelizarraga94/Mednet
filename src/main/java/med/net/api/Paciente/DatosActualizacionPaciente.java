package med.net.api.Paciente;

import med.net.api.Direccion.DatosDireccion;

public record DatosActualizacionPaciente(
        Long id,
        String nombre ,
        String telefono ,
        DatosDireccion direccion
) {
}
