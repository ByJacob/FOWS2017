package pl.edu.pwr.fows.fows2017.entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class People{
  @SerializedName("person")
  @Expose
  private String person;
  @SerializedName("namePL")
  @Expose
  private String namePL;
  @SerializedName("nameEN")
  @Expose
  private String nameEN;
  @SerializedName("picture")
  @Expose
  private String picture;
  public void setPerson(String person){
   this.person=person;
  }
  public String getPerson(){
   return person;
  }
  public void setNamePL(String namePL){
   this.namePL=namePL;
  }
  public String getNamePL(){
   return namePL;
  }
  public void setNameEN(String nameEN){
   this.nameEN=nameEN;
  }
  public String getNameEN(){
   return nameEN;
  }
  public void setPicture(String picture){
   this.picture=picture;
  }
  public String getPicture(){
   return picture;
  }
}