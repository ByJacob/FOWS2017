package pl.edu.pwr.fows.fows2017.entity;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class Menu {



    private final int id;
    private final String name;

    public Menu(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static final class Builder {
        private int id;
        private String name;

        private Builder() {
        }

        public static Builder aMenu() {
            return new Builder();
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Menu build() {
            return new Menu(id, name);
        }
    }
}
