package com.example.xzvxv3.controller;

import com.example.xzvxv3.PostRepository;
import com.example.xzvxv3.dto.PostDto;
import com.example.xzvxv3.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    private PostRepository postRepository;

    // 게시글 목록
    @GetMapping("/posts")
    public String posts(Model model) {
        List<Post> list = postRepository.findAll();
        model.addAttribute("postList", list);
        return "posts";
    }

    @GetMapping("/posts/new")
    public String newPostForm() {
        return "create";
    }

    @PostMapping("/posts/create")
    public String createPost(PostDto dto) {
        Post post = dto.toEntity(); // DTO -> Entity
        // log.info(post.toString());

        Post p = postRepository.save(post); // 레포지터리에 저장
        // log.info(p.toString());

        return "redirect:/posts";
    }

}
