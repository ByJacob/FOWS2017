package pl.edu.pwr.fows.fows2017.view.row;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public interface FragmentAgendaRowViewHeader {
    enum STATUS {BEFORE, NOW, AFTER};
    void displayDayHeader(String day);
    void displayTime(String time);
    void displaySpeaker(String name);
    void displayNameCompany(String name);
    void displayTheme(String theme);
    void setStatusLecture(STATUS statusLecture);
}
