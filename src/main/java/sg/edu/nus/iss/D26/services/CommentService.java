package sg.edu.nus.iss.D26.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.D26.models.Comment;
import sg.edu.nus.iss.D26.repositories.CommentRepository;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepo;

    public List<Comment> searchComments(String wordPhrases, Integer limit, Integer offset) {
        List<String> includes = new LinkedList<>();
        List<String> excludes = new LinkedList<>();
        // Process the string of words into individual words
        for (String word : wordPhrases.split(",")) {
            String wordTrim = word.trim();
            // Words starting with "-" are added into excludes list
            if (wordTrim.startsWith("-")) {
                excludes.add(wordTrim.substring(1));
            // Words without starting with "-" are added into includes list
            } else {
                includes.add(wordTrim);
            }
        }
        System.out.print(wordPhrases + "\n");
        System.out.println("PASSED SERVICE");
        return commentRepo.searchComments(includes, excludes, limit, offset);
    }
}
