package pl.edu.pwr.fows.fows2017.entity;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class Sponsor {
    private String name;
    private String url;

    public Sponsor(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }


    public static final class Builder {
        private String name;
        private String url;

        private Builder() {
        }

        public static Builder aSponsor() {
            return new Builder();
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withUrl(String url) {
            this.url = url;
            return this;
        }

        public Sponsor build() {
            Sponsor sponsor = new Sponsor(name, url);
            return sponsor;
        }
    }
}
