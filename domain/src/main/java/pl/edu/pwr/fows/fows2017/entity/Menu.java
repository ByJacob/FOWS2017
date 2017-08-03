package pl.edu.pwr.fows.fows2017.entity;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class Menu {



    private final int id;
    private final String tag;

    public Menu(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public static final class Builder {
        private int id;
        private String tag;

        private Builder() {
        }

        public static Builder aMenu() {
            return new Builder();
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withTag(String name) {
            this.tag = name;
            return this;
        }

        public Menu build() {
            return new Menu(id, tag);
        }
    }
}
