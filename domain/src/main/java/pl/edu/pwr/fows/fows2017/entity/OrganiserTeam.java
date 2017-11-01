package pl.edu.pwr.fows.fows2017.entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class OrganiserTeam{
  @SerializedName("namePL")
  @Expose
  private String namePL;
  @SerializedName("nameEN")
  @Expose
  private String nameEN;
  @SerializedName("people")
  @Expose
  private List<People> people;
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
  public void setPeople(List<People> people){
   this.people=people;
  }
  public List<People> getPeople(){
   return people;
  }
}