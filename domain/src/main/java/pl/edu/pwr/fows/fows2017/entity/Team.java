package pl.edu.pwr.fows.fows2017.entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Team{
  @SerializedName("person")
  @Expose
  private String person;
  @SerializedName("picture")
  @Expose
  private String picture;
  public void setPerson(String person){
   this.person=person;
  }
  public String getPerson(){
   return person;
  }
  public void setPicture(String picture){
   this.picture=picture;
  }
  public String getPicture(){
   return picture;
  }
}