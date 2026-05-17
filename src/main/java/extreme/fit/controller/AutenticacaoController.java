package extreme.fit.controller;

import extreme.fit.domain.usuario.DadosAutenticacao;
import extreme.fit.domain.usuario.Usuario;
import extreme.fit.domain.usuario.UsuarioRepository;
import extreme.fit.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository repository;



    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        System.out.println("Tentativa de login - usuário: " + dados.login());

        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);

        System.out.println("Autenticação bem-sucedida: " + authentication.isAuthenticated());

        return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
    }


}
