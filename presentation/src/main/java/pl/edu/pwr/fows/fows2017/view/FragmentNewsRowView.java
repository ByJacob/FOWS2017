package pl.edu.pwr.fows.fows2017.view;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.08.2017.
 */

public interface FragmentNewsRowView {

    void setDate(String date);
    void setPicture(String url, Integer position);
    void setMessage(String message);
    void openAppSendEmail(String email);
    void openFacebook(String url);
}
