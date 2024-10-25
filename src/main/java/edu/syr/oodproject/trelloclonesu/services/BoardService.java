package edu.syr.oodproject.trelloclonesu.services;

import edu.syr.oodproject.trelloclonesu.common.api.dao.CommonServiceAPI;
import edu.syr.oodproject.trelloclonesu.jpa.repository.BoardRepository;
import edu.syr.oodproject.trelloclonesu.models.Board;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BoardService implements CommonServiceAPI<Board> {

    @Autowired
    BoardRepository boardRepository;
    @Override
    public Optional<List<Board>> getAll() {
        return Optional.of(boardRepository.findAll());
    }

    @Override
    public Optional<Board> get(int id) {
        return Optional.empty();
    }

    @Override
    public void save(Board board) {

    }

    @Override
    public Optional<Board> update(Board board) {
        return Optional.empty();
    }

    @Override
    public void delete(Board board) {

    }
}
