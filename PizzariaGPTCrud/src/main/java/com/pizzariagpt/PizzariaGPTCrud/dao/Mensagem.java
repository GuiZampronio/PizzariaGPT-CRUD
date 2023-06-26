package com.pizzariagpt.PizzariaGPTCrud.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mensagem")
@Getter
public class Mensagem {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer mensagem_id;
        @Column(name = "respostamensagem")
        private String respostaMensagem;
        @Column(name = "mensagemrecebida")
        private String mensagemRecebida;
        @Column(name = "cliente_id")
        private Integer clientId;
        @Column(name = "timestamp")
        private Timestamp timestamp;
        @Column(name = "atendimentoconcluido")
        private Boolean atendimentoConcluido;
}
