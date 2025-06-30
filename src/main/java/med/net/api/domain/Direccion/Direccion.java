package med.net.api.domain.Direccion;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String codigo_postal;
    private String ciudad;
    private String provincia;

    public Direccion(DatosDireccion datosDireccion){
        this.calle = datosDireccion.calle();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
        this.barrio = datosDireccion.barrio();
        this.codigo_postal = datosDireccion.codigo_postal();
        this.ciudad = datosDireccion.ciudad();
        this.provincia = datosDireccion.provincia();
    }

    public void actualizarInformacion(@Valid DatosDireccion datos) {
        if(this.calle != null){
            this.calle = datos.calle();
        }
        if(this.numero != null){
            this.numero = datos.numero();
        }
        if(this.complemento != null){
            this.complemento = datos.complemento();
        }
        if(this.barrio != null){
            this.barrio = datos.barrio();
        }
        if(this.codigo_postal != null){
            this.codigo_postal = datos.codigo_postal();
        }
        if(this.ciudad != null){
            this.ciudad = datos.ciudad();
        }
        if(this.provincia != null){
            this.provincia = datos.provincia();
        }
    }
}
