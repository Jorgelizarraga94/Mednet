package med.net.api.Medico;

import med.net.api.Direccion.DatosDireccion;

public record DatosActualizacionMedico(
        Long id,
        String nombre ,
        String telefono,
        DatosDireccion datosDireccion
) {
}
