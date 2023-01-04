package sg.edu.nus.iss.D26.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import sg.edu.nus.iss.D26.models.Comment;
import sg.edu.nus.iss.D26.services.CommentService;

@RestController
@RequestMapping(path = "/api/comment")
public class CommentRestController {
    
    @Autowired
    private CommentService commentSvc;

    // localhost:8080/api/comment
    @GetMapping
    public ResponseEntity<String> searchComments(@RequestParam(required = true) String wordPhrases) {
        List<Comment> commentList = commentSvc.searchComments(wordPhrases, 10, 0);
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Comment comment : commentList) {
            jab.add(comment.toJSON());
        }
        JsonArray ja = jab.build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ja.toString());
    }

    // Text Index issue: Unable to query to database
}
