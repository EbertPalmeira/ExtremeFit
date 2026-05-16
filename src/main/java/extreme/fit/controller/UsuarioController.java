package extreme.fit.controller;

import extreme.fit.domain.usuario.DadosCadastroUsuario;
import extreme.fit.domain.usuario.Usuario;
import extreme.fit.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody DadosCadastroUsuario dados) {
        var senhaCriptografada = encoder.encode(dados.senha());
        var usuario = new Usuario(dados.login(), senhaCriptografada);
        repository.save(usuario);
        return ResponseEntity.ok().build();
    }
}