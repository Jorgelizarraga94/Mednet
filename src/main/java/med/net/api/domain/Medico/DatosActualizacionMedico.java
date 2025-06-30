package med.net.api.domain.Medico;

import med.net.api.domain.Direccion.DatosDireccion;

public record DatosActualizacionMedico(
        Long id,
        String nombre ,
        String telefono,
        DatosDireccion datosDireccion
) {
}
