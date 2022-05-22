package com.example.kafkaconsumer.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDTO2 {

    private String id;
    private String message;
    private String secondMessage;

}
