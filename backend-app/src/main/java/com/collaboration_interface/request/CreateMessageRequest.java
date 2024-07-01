package com.collaboration_interface.request;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageRequest {

    private long senderId;

    private long projectId;
     private String content;
}
