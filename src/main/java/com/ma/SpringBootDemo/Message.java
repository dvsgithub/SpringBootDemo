package com.ma.SpringBootDemo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String message;
    String user;
    Date msgDate;

    public Message(MessageDTO msgDto) {
        this.message = msgDto.message;
        this.user = msgDto.user;
    }

    @PrePersist
    void createAt() {
        this.msgDate = new Date();
    }

}
