package meesho.com.getprgithub.model;

import com.squareup.moshi.Json;

/**
 * Created by Sai on 13/06/18.
 */
public class PullRequest {
    @Json(name = "id")
    private String id;
    @Json(name = "title")
    private String title;
    @Json(name = "number")
    private int number;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }

    public User getUser() {
        return user;
    }

    @Json(name = "user")
    private User user;
//    @Json(name = "created_at")
//    private long createdAt;
}
