package com.pizzariagpt.PizzariaGPTCrud.repository;

import com.pizzariagpt.PizzariaGPTCrud.dao.Cliente;
import com.pizzariagpt.PizzariaGPTCrud.dao.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
        @Query(value = "SELECT c.id FROM public.cliente c WHERE c.client_number = ?1",
                  nativeQuery = true)
        Integer findCustomerIDByPhoneNumber(String phoneNumber);
}
