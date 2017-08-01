package io.joel.interfaces;

import io.joel.models.Comment;
import org.springframework.data.repository.CrudRepository;


public interface CommentRepository extends CrudRepository<Comment, Long> {
}
