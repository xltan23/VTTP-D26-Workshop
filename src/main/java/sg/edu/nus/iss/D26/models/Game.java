package sg.edu.nus.iss.D26.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {
    
    // Game members
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer users_rated;
    private String url;
    private String image;

    //Generate getter and setter
    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Integer getUsers_rated() {
        return users_rated;
    }
    public void setUsers_rated(Integer users_rated) {
        this.users_rated = users_rated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    // Create Game object from document retrieved from query
    public static Game create(Document document) {
        Game game = new Game();
        game.setGid(document.getInteger("gid"));
        game.setName(document.getString("name"));
        game.setYear(document.getInteger("year"));
        game.setRanking(document.getInteger("ranking"));
        game.setUsers_rated(document.getInteger("users_rated"));
        game.setUrl(document.getString("url"));
        game.setImage(document.getString("image"));
        return game;
    }

    // Create JsonObject from Game object 
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                    .add("gid", gid)
                    .add("name", name)
                    .add("year", year)
                    .add("ranking", ranking)
                    .add("users_rated", users_rated)
                    .add("url", url)
                    .add("image", image)
                    .build();
    }

    @Override
    public String toString() {
        return "Game [gid=" + gid + ", name=" + name + ", year=" + year + ", ranking=" + ranking + ", users_rated="
                + users_rated + ", url=" + url + ", image=" + image + "]";
    }
    
}
