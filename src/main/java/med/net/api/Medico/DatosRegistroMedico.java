package med.net.api.Medico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.net.api.Direccion.DatosDireccion;

public record DatosRegistroMedico(
        @NotBlank String nombre,     //NotBlanck no funciona para enums
        @NotBlank @Email String email,
        @NotBlank String telefono,
        @NotBlank @Pattern(regexp = "\\d{7,9}") String documento,
        @NotNull Especialidad especialidad,  //Not null funciona para enums
        @NotNull @Valid DatosDireccion direccion) {
}
