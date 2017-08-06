package pl.edu.pwr.fows.fows2017.view;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public interface DrawerMenuRowView {

    void displayTitle(String tag);

    void setIconToActive(Boolean isActive);

    void setTag(String string);
}
