package sg.edu.nus.iss.D26.models;

import java.util.List;

import org.joda.time.DateTime;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Games {
    
    // Games members 
    private List<Game> games;
    private Integer limit;
    private Integer offset;
    private Integer total;
    private DateTime timestamp;

    // Generate getter and setter
    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public DateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Create JsonObject from Games object
    public JsonObject toJSON() {
        List<JsonObject> gamesJsonList = games.stream().map(g -> g.toJSON()).toList();
        return Json.createObjectBuilder()
                    .add("games", gamesJsonList.toString())
                    .add("limit", limit)
                    .add("offset", offset)
                    .add("total", total)
                    .add("timestamp", timestamp.toString())
                    .build();
    }

}
