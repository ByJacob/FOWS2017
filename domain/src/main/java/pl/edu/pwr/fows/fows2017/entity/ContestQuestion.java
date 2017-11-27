package pl.edu.pwr.fows.fows2017.entity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class ContestQuestion{
  @SerializedName("questionPL")
  @Expose
  private String questionPL;
  @SerializedName("answer")
  @Expose
  private ArrayList<String> answer;
  public void setQuestionPL(String questionPL){
   this.questionPL=questionPL;
  }
  public String getQuestionPL(){
   return questionPL;
  }
  public void setAnswer(ArrayList<String> answer){
   this.answer=answer;
  }
  public ArrayList<String> getAnswer(){
   return answer;
  }
}