package pl.edu.pwr.fows.fows2017.entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class Questionnaire{
  @SerializedName("question")
  @Expose
  private List<Question> question;
  @SerializedName("version")
  @Expose
  private Double version;
  public void setQuestion(List<Question> question){
   this.question=question;
  }
  public List<Question> getQuestion(){
   return question;
  }
  public void setVersion(Double version){
   this.version=version;
  }
  public Double getVersion(){
   return version;
  }
}