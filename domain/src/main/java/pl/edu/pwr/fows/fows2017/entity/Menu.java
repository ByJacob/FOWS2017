package pl.edu.pwr.fows.fows2017.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 */
public class Menu {
    @SerializedName("positionInMainMenu")
    @Expose
    private Integer positionInMainMenu;
    @SerializedName("enable")
    @Expose
    private Boolean enable;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("tag")
    @Expose
    private String tag;

    public Integer getPositionInMainMenu() {
        return positionInMainMenu;
    }

    public void setPositionInMainMenu(Integer positionInMainMenu) {
        this.positionInMainMenu = positionInMainMenu;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static final class Builder {
        private Integer positionInMainMenu;
        private Boolean enable;
        private Integer position;
        private String tag;

        private Builder() {
        }

        public static Builder aMenu() {
            return new Builder();
        }

        public Builder withPositionInMainMenu(Integer positionInMainMenu) {
            this.positionInMainMenu = positionInMainMenu;
            return this;
        }

        public Builder withEnable(Boolean enable) {
            this.enable = enable;
            return this;
        }

        public Builder withPosition(Integer position) {
            this.position = position;
            return this;
        }

        public Builder withTag(String tag) {
            this.tag = tag;
            return this;
        }

        public Menu build() {
            Menu menu = new Menu();
            if(positionInMainMenu==null)
                positionInMainMenu=0;
            menu.setPositionInMainMenu(positionInMainMenu);
            menu.setEnable(enable);
            menu.setPosition(position);
            menu.setTag(tag);
            return menu;
        }
    }
}