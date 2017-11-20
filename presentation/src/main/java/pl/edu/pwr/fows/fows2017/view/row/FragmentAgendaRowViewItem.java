package pl.edu.pwr.fows.fows2017.view.row;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public interface FragmentAgendaRowViewItem {
    void displayDescription(String description);

    void displayLogo(String urlLogo);

    void setStatusLecture(FragmentAgendaRowViewHeader.STATUS statusLecture);

    void displayTheme(String theme);
}
