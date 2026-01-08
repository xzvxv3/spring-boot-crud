package com.example.xzvxv3.controller;

import com.example.xzvxv3.PostRepository;
import com.example.xzvxv3.dto.PostDto;
import com.example.xzvxv3.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class BoardController {

    @Autowired
    private PostRepository postRepository;

    // 게시글 목록, read
    @GetMapping("/posts")
    public String posts(Model model) {
        List<Post> list = postRepository.findAll();
        model.addAttribute("postList", list);
        return "posts";
    }

    // create
    @GetMapping("/posts/new")
    public String newPostForm() {
        return "create";
    }

    // create
    @PostMapping("/posts/create")
    public String createPost(PostDto dto) {
        Post post = dto.toEntity(); // DTO -> Entity
        // log.info(post.toString());

        Post p = postRepository.save(post); // 레포지터리에 저장
        // log.info(p.toString());

        return "redirect:/posts";
    }

    // read
    @GetMapping("/posts/{id}")
    public String read(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);

        model.addAttribute("post", post);

        return "read";
    }

    // update
    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);

        model.addAttribute("post", post);

        return "edit";
    }

    // update
    @PostMapping("/posts/update")
    public String update(PostDto dto) {
        Post post = dto.toEntity();

        if(postRepository.findById(post.getId()).orElse(null) != null) {
            postRepository.save(post);
        }

        return "redirect:/posts/" + post.getId();
    }

    // Delete
    @GetMapping("/posts/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        Post post = postRepository.findById(id).orElse(null);

        if(post != null) {
            postRepository.delete(post);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }

        return "redirect:/posts";
    }
}
