package com.pizzariagpt.PizzariaGPTCrud.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pizzariagpt.PizzariaGPTCrud.dao.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
        public String gerarToken(Usuario usuario){
                return JWT.create()
                          .withIssuer("PizzariaGPT")
                          .withSubject(usuario.getUsername())
                          .withClaim("id", usuario.getId())
                          .withExpiresAt(LocalDateTime.now()
                                    .plusMinutes(50)
                                    .toInstant(ZoneOffset.of("-03:00"))
                          )
                          .sign(Algorithm.HMAC256("jwtsecreto"));
        }

        public String getSubject(String token) {
                return JWT.require(Algorithm.HMAC256("jwtsecreto"))
                          .withIssuer("PizzariaGPT")
                          .build().verify(token).getSubject();

        }
}
