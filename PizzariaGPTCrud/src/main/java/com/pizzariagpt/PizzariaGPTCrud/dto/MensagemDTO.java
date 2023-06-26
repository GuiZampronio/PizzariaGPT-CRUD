package com.pizzariagpt.PizzariaGPTCrud.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@JsonSerialize
public class MensagemDTO {
        private String mensagemRecebida;
        private String respostaMensagem;
        private Timestamp timestamp;
}
