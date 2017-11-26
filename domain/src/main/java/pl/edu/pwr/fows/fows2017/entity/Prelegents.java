package pl.edu.pwr.fows.fows2017.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Awesome Pojo Generator
 */
public class Prelegents {
    @SerializedName("descriptionEN")
    @Expose
    private String descriptionEN;
    @SerializedName("prelegent")
    @Expose
    private String prelegent;
    @SerializedName("themePL")
    @Expose
    private String themePL;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("descriptionPL")
    @Expose
    private String descriptionPL;
    @SerializedName("themeEN")
    @Expose
    private String themeEN;

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getPrelegent() {
        return prelegent;
    }

    public void setPrelegent(String prelegent) {
        this.prelegent = prelegent;
    }

    public String getThemePL() {
        return themePL;
    }

    public void setThemePL(String themePL) {
        this.themePL = themePL;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime(String date) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm", Locale.US);
        try {
            return df.parse(date + " " + startTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime(String date) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy' 'HH:mm", Locale.US);
        try {
            return df.parse(date + " " + endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDescriptionPL() {
        return descriptionPL;
    }

    public void setDescriptionPL(String descriptionPL) {
        this.descriptionPL = descriptionPL;
    }

    public String getThemeEN() {
        return themeEN;
    }

    public void setThemeEN(String themeEN) {
        this.themeEN = themeEN;
    }
}