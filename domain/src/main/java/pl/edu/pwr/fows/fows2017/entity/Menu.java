package pl.edu.pwr.fows.fows2017.entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Menu{
  @SerializedName("enable")
  @Expose
  private Boolean enable;
  @SerializedName("position")
  @Expose
  private Integer position;
  @SerializedName("tag")
  @Expose
  private String tag;
  public void setEnable(Boolean enable){
   this.enable=enable;
  }
  public Boolean getEnable(){
   return enable;
  }
  public void setPosition(Integer position){
   this.position=position;
  }
  public Integer getPosition(){
   return position;
  }
  public void setTag(String tag){
   this.tag=tag;
  }
  public String getTag(){
   return tag;
  }


    public static final class Builder {
        private Boolean enable;
        private Integer position;
        private String tag;

        private Builder() {
        }

        public static Builder aMenu() {
            return new Builder();
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
            menu.setEnable(enable);
            menu.setPosition(position);
            menu.setTag(tag);
            return menu;
        }
    }
}