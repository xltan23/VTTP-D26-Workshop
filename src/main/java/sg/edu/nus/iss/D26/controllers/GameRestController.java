package sg.edu.nus.iss.D26.controllers;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.D26.models.Game;
import sg.edu.nus.iss.D26.models.Games;
import sg.edu.nus.iss.D26.services.GameService;

@RestController
@RequestMapping(path = "/api/game")
public class GameRestController {
    
    @Autowired
    private GameService gameSvc;

    // localhost:8080/api/game/search?limit=<limit>&offset=<offset>
    @GetMapping(path = "/search")
    public ResponseEntity<String> searchGames(@RequestParam Integer limit, @RequestParam Integer offset) {
        // Retrieve Game list from query
        List<Game> gameList = gameSvc.searchGames(limit, offset);
        // Creating Game list array        
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Game game : gameList) {
            jab.add(game.toJSON());
        }
        JsonArray ja = jab.build();
        // Creating Games object
        Games games = new Games();
        games.setGames(gameList);
        games.setLimit(limit);
        games.setOffset(offset);
        games.setTotal(gameList.size());
        games.setTimestamp(DateTime.now());
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonObject jo = job.add("Games", games.toJSON()).build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ja.toString()+jo.toString());
    }

    // localhost:8080/api/game/search/<gid>
    @GetMapping(path = "/search/{gid}")
    public ResponseEntity<String> searchGameByID(@PathVariable Integer gid) {
        // Retrieve Game from query
        Game game = gameSvc.searchGameByID(gid);
        JsonObject jo = Json.createObjectBuilder()
                            .add("Game", game.toJSON())
                            .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jo.toString());
    }
}
