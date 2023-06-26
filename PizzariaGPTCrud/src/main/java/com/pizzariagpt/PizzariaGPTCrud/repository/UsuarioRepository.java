package com.pizzariagpt.PizzariaGPTCrud.repository;

import com.pizzariagpt.PizzariaGPTCrud.dao.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        Usuario findByLogin(String login);


}
