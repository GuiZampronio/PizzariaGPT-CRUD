package com.pizzariagpt.PizzariaGPTCrud.repository;

import com.pizzariagpt.PizzariaGPTCrud.dao.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem,Long> {
        @Query(value = "SELECT * FROM public.mensagem m WHERE m.cliente_id = ?1 ORDER BY m.timestamp ASC",
                  nativeQuery = true)
        List<Mensagem> findMessagesByCustomerID(Integer customerId);
}
