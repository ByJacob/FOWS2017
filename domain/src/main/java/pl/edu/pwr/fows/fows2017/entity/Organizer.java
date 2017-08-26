package pl.edu.pwr.fows.fows2017.entity;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public class Organizer {
    private String firstName;
    private String lastName;
    private String email;
    private String urlPicture;
    private TYPE type;
    private String rolePL;
    private String roleEN;
    public Organizer(String firstName, String lastName, String email, String urlPicture, TYPE type, String rolePL, String roleEN) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.urlPicture = urlPicture;
        this.type = type;
        this.rolePL = rolePL;
        this.roleEN = roleEN;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public TYPE getType() {
        return type;
    }

    public String getRolePL() {
        return rolePL;
    }

    public String getRoleEN() {
        return roleEN;
    }

    public enum TYPE {PERSON, FACEBOOK}

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String urlPicture;
        private TYPE type;
        private String rolePL;
        private String roleEN;

        private Builder() {
        }

        public static Builder anOrganizer() {
            return new Builder();
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withUrlPicture(String urlPicture) {
            this.urlPicture = urlPicture;
            return this;
        }

        public Builder withType(TYPE type) {
            this.type = type;
            return this;
        }

        public Builder withRolePL(String rolePL) {
            this.rolePL = rolePL;
            return this;
        }

        public Builder withRoleEN(String roleEN) {
            this.roleEN = roleEN;
            return this;
        }

        public Organizer build() {
            return new Organizer(firstName, lastName, email, urlPicture, type, rolePL, roleEN);
        }
    }
}
