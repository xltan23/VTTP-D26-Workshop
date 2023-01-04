package sg.edu.nus.iss.D26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.D26.models.Comment;

@Repository
public class CommentRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Comment> searchComments(List<String> includes, List<String> excludes, Integer limit, Integer offset) {
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage()
                                                // Find any text which match any in the includes array and not match any in exclude array
                                                .matchingAny(includes.toArray(new String[includes.size()]))
                                                .notMatchingAny(excludes.toArray(new String[excludes.size()]));
        Query textQuery = TextQuery.queryText(textCriteria)
                                        .includeScore()
                                        .sortByScore()
                                        .limit(limit)
                                        .skip(offset);
        // Test checks                                
        System.out.println(textQuery);
        System.out.println("PASSED REPOSITORY");
        // Submit query and convert the returned list of documents into Comment objects
        return mongoTemplate.find(textQuery, Document.class, "comments")
                            .stream()
                            .map(d -> Comment.create(d))
                            .toList();
    }
}
