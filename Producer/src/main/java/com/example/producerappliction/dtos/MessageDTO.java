package com.example.producerappliction.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDTO {

    private String id;
    private String message;
    private String secondMessage;

}
