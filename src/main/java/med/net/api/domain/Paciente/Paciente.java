package med.net.api.domain.Paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.net.api.domain.Direccion.Direccion;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean activo;
    private String nombre;
    private String email;
    private String documento_identidad;
    private String telefono;

    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datos) {
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento_identidad = datos.documento_identidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public Long getId() {
        return id;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento_identidad() {
        return documento_identidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void actualizarPaciente(DatosActualizacionPaciente datos) {
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if(datos.telefono() != null){
            this.telefono = datos.telefono();
        }
        if(datos.direccion() != null){
            this.direccion.actualizarInformacion(datos.direccion());
        }
    }

    public void eliminarPaciente() {
        this.activo = false;
    }
}
