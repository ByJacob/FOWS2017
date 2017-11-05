package pl.edu.pwr.fows.fows2017.entity;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

public class User {
    private String displayName;
    private String uid;
    private String email;
    private String password;
    private Boolean isVerified;

    public User(String displayName, String uid, String email, String password, Boolean isVerified) {
        this.displayName = displayName;
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.isVerified = isVerified;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getPassword() {
        return password;
    }

    public static final class Builder {
        private String displayName;
        private String uid;
        private String email;
        private String password;
        private Boolean isVerified;

        private Builder() {
        }

        public static Builder anUser() {
            return new Builder();
        }

        public Builder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder withUid(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withIsVerified(Boolean isVerified) {
            this.isVerified = isVerified;
            return this;
        }

        public User build() {
            return new User(displayName, uid, email, password, isVerified);
        }
    }
}
