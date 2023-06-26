package com.pizzariagpt.PizzariaGPTCrud.controller;

import com.pizzariagpt.PizzariaGPTCrud.dao.Cliente;
import com.pizzariagpt.PizzariaGPTCrud.dao.Mensagem;
import com.pizzariagpt.PizzariaGPTCrud.dto.MensagemDTO;
import com.pizzariagpt.PizzariaGPTCrud.repository.ClienteRepository;
import com.pizzariagpt.PizzariaGPTCrud.repository.MensagemRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mensagem")
@CrossOrigin(origins = "http://localhost:4200")
@Log4j2
public class MensagemController {

        @Autowired
        MensagemRepository mensagemRepository;

        @Autowired
        ClienteRepository clienteRepository;

        @GetMapping()
        @CrossOrigin(origins = "http://localhost:4200")
        public List<MensagemDTO> getMessagesByPhoneNumber(@RequestParam String phoneNumber){
                Integer customerId = clienteRepository.findCustomerIDByPhoneNumber(phoneNumber);

                List<Mensagem> messagesList = mensagemRepository.findMessagesByCustomerID(customerId);

                List<MensagemDTO> messagesDTO = new ArrayList<>();

                for (Mensagem message : messagesList){
                        messagesDTO.add(MensagemDTO.builder()
                                  .mensagemRecebida(message.getMensagemRecebida())
                                  .respostaMensagem(message.getRespostaMensagem())
                                  .timestamp(message.getTimestamp())
                                  .build()
                        );
                }

                return messagesDTO;

        }

}
