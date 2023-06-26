package com.pizzariagpt.PizzariaGPTCrud.controller;

import com.pizzariagpt.PizzariaGPTCrud.dao.Usuario;
import com.pizzariagpt.PizzariaGPTCrud.dto.LoginDTO;
import com.pizzariagpt.PizzariaGPTCrud.dto.RegisterDTO;
import com.pizzariagpt.PizzariaGPTCrud.repository.UsuarioRepository;
import com.pizzariagpt.PizzariaGPTCrud.service.TokenService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.HttpStatusCodeException;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@Log4j2
public class AuthController {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private TokenService tokenService;

        @Autowired
        BCryptPasswordEncoder encoder;

        @Autowired
        UsuarioRepository usuarioRepository;

        @PostMapping("/login")
        @CrossOrigin(origins = "http://localhost:4200")
        public String login(@RequestBody LoginDTO login){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
                          UsernamePasswordAuthenticationToken(login.username(),
                          login.password());

                 Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

                 var usuario = (Usuario) authenticate.getPrincipal();

                 log.info("Gerando Token...");
                 return tokenService.gerarToken(usuario);
        }

        @PostMapping("/register")
        @CrossOrigin(origins = "http://localhost:4200")
        public String register(@RequestBody RegisterDTO newUser) {
                Usuario usuario = new Usuario();
                usuario.setEmail(newUser.getEmail());
                usuario.setLogin(newUser.getUsername());
                usuario.setPassword(encoder.encode(newUser.getPassword()));

                Boolean doesUsernameExists = usuarioRepository.checkIfUsernameExist(newUser.getUsername());
                if(doesUsernameExists.equals(Boolean.TRUE)){
                        throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED) {};
                }else{
                        Usuario usuarioSalvo = usuarioRepository.save(usuario);
                        return tokenService.gerarToken(usuarioSalvo);
                }
        }


}
