package com.example.xzvxv3.dto;

import com.example.xzvxv3.entity.Post;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class PostDto {
    private Long id;
    private String title;
    private String content;

    public Post toEntity() {
        return new Post(id, title, content);
    }
}
