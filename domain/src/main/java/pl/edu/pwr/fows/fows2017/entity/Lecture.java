package pl.edu.pwr.fows.fows2017.entity;

import java.util.Date;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public class Lecture {
    private Date startTime;
    private Date endTime;
    private String speaker;
    private String descriptionPL;
    private String descriptionEN;
    private String themeEN;
    private String themePL;
    private String logoURL;
    private String speakerPicture;
    private String speakerPictureSmall;
    private String company;

    public Lecture(Date startTime, Date endTime, String speaker, String descriptionPL,
                   String descriptionEN, String themeEN, String themePL, String logoURL, String speakerPicture,
                   String speakerPictureSmall, String company) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.speaker = speaker;
        this.descriptionPL = descriptionPL;
        this.descriptionEN = descriptionEN;
        this.themeEN = themeEN;
        this.themePL = themePL;
        this.logoURL = logoURL;
        this.speakerPicture = speakerPicture;
        this.speakerPictureSmall = speakerPictureSmall;
        this.company = company;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getDescriptionPL() {
        return descriptionPL;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public String getSpeakerPicture() {
        return speakerPicture;
    }

    public String getSpeakerPictureSmall() {
        return speakerPictureSmall;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public String getCompany() {
        return company;
    }

    public String getThemeEN() {
        return themeEN;
    }

    public String getThemePL() {
        return themePL;
    }


    public static final class Builder {
        private Date startTime;
        private Date endTime;
        private String speaker;
        private String descriptionPL;
        private String descriptionEN;
        private String themeEN;
        private String themePL;
        private String logoURL;
        private String speakerPicture;
        private String speakerPictureSmall;
        private String company;

        private Builder() {
        }

        public static Builder aLecture() {
            return new Builder();
        }

        public Builder withStartTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(Date endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder withSpeaker(String speaker) {
            this.speaker = speaker;
            return this;
        }

        public Builder withDescriptionPL(String descriptionPL) {
            this.descriptionPL = descriptionPL;
            return this;
        }

        public Builder withDescriptionEN(String descriptionEN) {
            this.descriptionEN = descriptionEN;
            return this;
        }

        public Builder withThemeEN(String themeEN) {
            this.themeEN = themeEN;
            return this;
        }

        public Builder withThemePL(String themePL) {
            this.themePL = themePL;
            return this;
        }

        public Builder withLogoURL(String logoURL) {
            this.logoURL = logoURL;
            return this;
        }

        public Builder withSpeakerPicture(String speakerPicture) {
            this.speakerPicture = speakerPicture;
            return this;
        }

        public Builder withSpeakerPictureSmall(String speakerPictureSmall) {
            this.speakerPictureSmall = speakerPictureSmall;
            return this;
        }

        public Builder withCompany(String company) {
            this.company = company;
            return this;
        }

        public Lecture build() {
            return new Lecture(startTime, endTime, speaker, descriptionPL, descriptionEN, themeEN, themePL, logoURL, speakerPicture, speakerPictureSmall, company);
        }
    }
}
