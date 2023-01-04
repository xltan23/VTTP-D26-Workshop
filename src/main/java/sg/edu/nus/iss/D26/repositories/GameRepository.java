package sg.edu.nus.iss.D26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.D26.models.Game;

@Repository
public class GameRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Game> searchGames(Integer limit, Integer offset) {
        Query query = new Query(Criteria.where("ranking").exists(true))
                            // Sort by ranking, skip offset and set limit
                            .with(Sort.by(Sort.Direction.ASC,"ranking"))
                            .skip(offset)
                            .limit(limit);
        // Query should return multiple Game documents to be converted to Games list
        return mongoTemplate.find(query, Document.class, "games")
                                .stream()
                                .map(d -> Game.create(d))
                                .toList();
    }

    // Search for specific games by taking in ID as input
    public Game searchGameByID(Integer gid) {
        Query query = new Query();
        query.addCriteria(Criteria.where("gid").is(gid));
        // find returns object list
        // findOne returns one object
        return mongoTemplate.findOne(query, Game.class, "games");
    }
}
