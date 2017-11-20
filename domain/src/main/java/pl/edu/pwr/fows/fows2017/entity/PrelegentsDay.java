package pl.edu.pwr.fows.fows2017.entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class PrelegentsDay{
  @SerializedName("date")
  @Expose
  private String date;
  @SerializedName("prelegents")
  @Expose
  private List<Prelegents> prelegents;
  public void setDate(String date){
   this.date=date;
  }
  public String getDate(){
   return date;
  }
  public void setPrelegents(List<Prelegents> prelegents){
   this.prelegents=prelegents;
  }
  public List<Prelegents> getPrelegents(){
   return prelegents;
  }
}