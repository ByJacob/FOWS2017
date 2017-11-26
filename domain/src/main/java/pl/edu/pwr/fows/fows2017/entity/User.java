package pl.edu.pwr.fows.fows2017.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 */
public class User {
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("university")
    @Expose
    private String university;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("verify")
    @Expose
    private Boolean verify;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("email")
    @Expose
    private String email;
    private String password;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVerify() {
        return verify;
    }

    public void setVerify(Boolean verify) {
        this.verify = verify;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public static final class Builder {
        private String surname;
        private String university;
        private String name;
        private Boolean verify;
        private String company;
        private String email;
        private String password;

        private Builder() {
        }

        public static Builder anUser() {
            return new Builder();
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withUniversity(String university) {
            this.university = university;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withVerify(Boolean verify) {
            this.verify = verify;
            return this;
        }

        public Builder withCompany(String company) {
            this.company = company;
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

        public User build() {
            User user = new User();
            user.setSurname(surname);
            user.setUniversity(university);
            user.setName(name);
            user.setVerify(verify);
            user.setCompany(company);
            user.setEmail(email);
            user.setPassword(password);
            return user;
        }
    }
}