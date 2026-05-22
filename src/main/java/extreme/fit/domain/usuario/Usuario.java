package extreme.fit.domain.usuario;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@Entity(name = "Usuario")
@EqualsAndHashCode(of = "id")
public class Usuario {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;



    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    public Usuario(String login, String senhaCriptografada) {
        this.login = login;
        this.senha = senhaCriptografada;
    }
}
