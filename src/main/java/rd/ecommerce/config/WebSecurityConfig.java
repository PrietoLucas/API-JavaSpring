package rd.ecommerce.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void  configure(HttpSecurity http) throws Exception {
        http
                //Pedir que todas rotas sejam autorizadas
                .authorizeRequests().anyRequest().authenticated()

                //Tirar sessão da aplicação
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //Ligar Config do Http Basic
                .and()
                      .httpBasic()

                //Desabilitar o CSRF token
                .and()
                      .csrf().disable();
    }

    @Autowired
    protected void criarUsuarios(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("victor")
                .password(encoder.encode("segredo")).roles("USER");
    }
}
