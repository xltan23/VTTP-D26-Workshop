package sg.edu.nus.iss.D26.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    
    // Comment members
    private String c_id;
    private String user;
    private Integer rating;
    private String c_text;
    private Integer gid;

    // Generate getter and setter
    public String getC_id() {
        return c_id;
    }
    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    public String getC_text() {
        return c_text;
    }
    public void setC_text(String c_text) {
        this.c_text = c_text;
    }
    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    // Create Comment object from Document retrieved
    public static Comment create(Document document) {
        Comment comment = new Comment();
        comment.setC_id(document.getString("c_id"));
        comment.setUser(document.getString("user"));
        comment.setRating(document.getInteger("rating"));
        comment.setC_text(document.getString("c_text"));
        comment.setGid(document.getInteger("gid"));
        return comment;
    }

    // Create JsonObject from Comment object
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                    .add("c_id", c_id)
                    .add("user", user)
                    .add("rating", rating)
                    .add("c_text", c_text)
                    .add("gid", gid)
                    .build();
    }
    
    @Override
    public String toString() {
        return "Comment [c_id=" + c_id + ", user=" + user + ", rating=" + rating + ", c_text=" + c_text + ", gid=" + gid
                + "]";
    }

    
}
