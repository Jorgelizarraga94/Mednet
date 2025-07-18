package med.net.api.controller;

import jakarta.validation.Valid;
import med.net.api.domain.Usuario.DatosAutenticacion;
import med.net.api.domain.Usuario.Usuario;
import med.net.api.infra.security.DatosTokenJwt;
import med.net.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager manager;
    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datos){
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasenia());
        var autenticacion = manager.authenticate(authenticationToken);

        var tokenJwt = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJwt(tokenJwt));
    }
}
