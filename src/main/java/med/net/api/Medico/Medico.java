package med.net.api.Medico;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.annotation.Generated;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.net.api.Direccion.DatosDireccion;
import med.net.api.Direccion.Direccion;

@Table(name="medicos")
@Entity(name="medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //reconoce si los objetos son iguales si coincide el id
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean activo;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datos) {
        this.id = null;
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documento = datos.documento();
        this.especialidad = datos.especialidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void actualizarInformacion(@Valid DatosActualizacionMedico datos) {
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if(datos.telefono() != null){
            this.telefono = datos.telefono();
        }
        if(datos.datosDireccion() != null){
            this.direccion.actualizarInformacion(datos.datosDireccion());
        }
    }

    public void eliminar() {
        this.activo = false;
    }
}
