package pl.edu.pwr.fows.fows2017.map;

import pl.edu.pwr.fows.fows2017.R;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class DrawerMenuItemMap {

    private static final String[] tags = {"HOME", "AGENDA", "SPONSORS", "CONTACT", "OFFER"};
    private static final Integer[] idStrings = {R.string.nav_item_home, R.string.nav_item_agenda,
                        R.string.nav_item_sponsors, R.string.nav_item_contact, R.string.nav_item_offer};
    private static final Integer[] idFragments = {0};

    public static Integer getTitle(String tag) {
        for(int i=0; i<tags.length;  i++){
            if(tag.equals(tags[i])) {
                return idStrings[i];
            }
        }
        return null; //todo make exception
    }

    public static Integer getFragmentId(String tag) { //todo implement method when add fragments
        for(int i=0; i<tags.length; i++) {
            if(tag.equals(tags[i]))
                return idFragments[i];
        }
        return null; //todo make exception
    }
}
