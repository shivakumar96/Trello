package edu.syr.oodproject.trelloclonesu.controller;
import edu.syr.oodproject.trelloclonesu.models.Board;
import edu.syr.oodproject.trelloclonesu.services.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/app/v1")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping(path = "/boards")
    public ResponseEntity<List<Board>> getAllBoards(){
        List<Board> boardList = boardService.getAll().orElse(new ArrayList<Board>());
        return new ResponseEntity<>(boardList, HttpStatus.OK);
    }

    @GetMapping(path = "/boards/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable int id) {
        Board board = boardService.get(id).orElseThrow(()->new RuntimeException("board not found"));
        return new ResponseEntity<>(board, HttpStatus.OK);
    }

    @PostMapping(path = "/boards")
    public ResponseEntity<Board> addBoard(@Valid  @RequestBody Board board){
        boardService.update(board);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(board.getBoardID())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/boards/{id}")
    public ResponseEntity<String> deleteBoard( @PathVariable int id){
        Board board = boardService.get(id).orElseThrow(()->new RuntimeException("board not found"));
        boardService.delete(board);
        return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
    }
}
