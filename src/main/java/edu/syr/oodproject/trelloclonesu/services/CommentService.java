package edu.syr.oodproject.trelloclonesu.services;

import edu.syr.oodproject.trelloclonesu.models.Comment;
import edu.syr.oodproject.trelloclonesu.common.api.dao.CommonServiceAPI;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements CommonServiceAPI<Comment> {

    @Override
    public Optional<List<Comment>> getAll() {
        return null;
    }

    @Override
    public Optional<Comment> get(int id) {
        return null;
    }

    @Override
    public void save(Comment comment) {
    }

    @Override
    public Optional<Comment> update(Comment comment) {

        return null;
    }

    @Override
    public void delete(Comment comment) {
    }
}
