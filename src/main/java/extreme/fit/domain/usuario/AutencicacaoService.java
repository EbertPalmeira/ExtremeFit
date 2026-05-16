package extreme.fit.domain.usuario;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AutencicacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Buscando usuário: " + username);

        var usuario = repository.findByLogin(username)
                .orElseThrow(() -> {
                    System.out.println("Usuário NÃO encontrado: " + username);
                    return new UsernameNotFoundException("Usuário não encontrado");
                });

        System.out.println("Usuário encontrado: " + usuario.getLogin());
        System.out.println("Senha no banco: " + usuario.getSenha());

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles("USER")
                .build();
    }
}
