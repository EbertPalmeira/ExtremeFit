package extreme.fit.controller;

import extreme.fit.domain.usuario.DadosAutenticacao;

import extreme.fit.domain.usuario.UsuarioRepository;
import extreme.fit.infra.security.DadosTokenJWT;
import extreme.fit.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

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

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var usuario = repository.findByLogin(dados.login())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        var tokenJWT = tokenService.gerarToken(usuario);

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }


}
