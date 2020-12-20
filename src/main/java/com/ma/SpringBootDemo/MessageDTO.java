package com.ma.SpringBootDemo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {
    String message;
    String user;
}
