package com.example.xzvxv3;

import com.example.xzvxv3.entity.Post;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Override
    List<Post> findAll();
}
