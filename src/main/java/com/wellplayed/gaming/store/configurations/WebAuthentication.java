package com.wellplayed.gaming.store.configurations;

import com.wellplayed.gaming.store.models.Client;
import com.wellplayed.gaming.store.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    private ClientRepository clientRepository;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputName-> {
            Client client = clientRepository.findByEmail(inputName);
            if (client != null) {
                if (client.getEmail().equals("admin@admin.com")){
                    return new User(client.getEmail(),client.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                }
                else{
                    return new User(client.getEmail(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("CLIENT"));
                }
            } else {
                throw new UsernameNotFoundException("Unknown customer: " + inputName);
            }
        });
    }
}