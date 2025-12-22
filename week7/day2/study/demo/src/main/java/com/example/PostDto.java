package com.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostDto {
    private Integer id;
    private int userId;
    private String title;
    private String body;
}
