package com.pizzariagpt.PizzariaGPTCrud.repository;

import com.pizzariagpt.PizzariaGPTCrud.dao.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
        Usuario findByLogin(String login);

        @Query(value = "SELECT EXISTS(SELECT * FROM public.usuarios u WHERE u.login = ?1)",
                  nativeQuery = true)
        Boolean checkIfUsernameExist(String username);


}
