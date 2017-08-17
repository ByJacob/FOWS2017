package pl.edu.pwr.fows.fows2017.view.row;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public interface FragmentContactRowView {
    void displayName(String name);
    void displayRole(String role);
    void displayPicture(String urlPicture);
    void setActionToItem(String url);

    void changeIconImage(String urlPicture);
}
