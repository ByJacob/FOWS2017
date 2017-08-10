package pl.edu.pwr.fows.fows2017.entity;

import java.util.Date;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FacebookPost {

    private String full_picture;
    private String message;
    private Date created_time;
    private String picture;
    private String link;
    private String story;
    private Integer id;

    public FacebookPost(String full_picture, String message, Date created_time, String picture, String link, String story, Integer id) {
        this.full_picture = full_picture;
        this.message = message;
        this.created_time = created_time;
        this.picture = picture;
        this.link = link;
        this.story = story;
        this.id = id;
    }

    public String getFullPicture() {
        return full_picture;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreatedTime() {
        return created_time;
    }

    public String getPicture() {
        return picture;
    }

    public String getLink() {
        return link;
    }

    public String getStory() {
        return story;
    }

    public Integer getId() {
        return id;
    }


    public static final class Builder {
        private String full_picture;
        private String message;
        private Date created_time;
        private String picture;
        private String link;
        private String story;
        private Integer id;

        private Builder() {
        }

        public static Builder aFacebookPost() {
            return new Builder();
        }

        public Builder withFullPicture(String full_picture) {
            this.full_picture = full_picture;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withCreatedTime(Date created_time) {
            this.created_time = created_time;
            return this;
        }

        public Builder withPicture(String picture) {
            this.picture = picture;
            return this;
        }

        public Builder withLink(String link) {
            this.link = link;
            return this;
        }

        public Builder withStory(String story) {
            this.story = story;
            return this;
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public FacebookPost build() {
            return new FacebookPost(full_picture, message, created_time, picture, link, story, id);
        }
    }
}
