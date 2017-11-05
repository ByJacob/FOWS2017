package pl.edu.pwr.fows.fows2017.map;

import pl.edu.pwr.fows.fows2017.R;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class DrawerMenuItemMap {

    private static final String[] tags = {"HOME", "AGENDA", "SPONSORS", "CONTACT", "OFFER", "NEWS",
            "LOCATION", "QUESTIONNAIRE", "ORGANISERS", "LOGIN", "CREATE_ACCOUNT", "ACCOUNT", "SIGN_OUT"};
    private static final Integer[] idStrings = {R.string.menu_item_home, R.string.menu_item_agenda,
                        R.string.menu_item_sponsors, R.string.menu_item_contact, R.string.menu_item_offer, R.string.menu_item_news, R.string.menu_item_location, R.string.menu_item_questionnaire, R.string.menu_item_organisers,
                        R.string.login, R.string.createAccount, R.string.account, R.string.signOut};

    private static final Integer[] idIcons= {R.drawable.ic_home, R.drawable.ic_agenda, R.drawable.ic_sponsors,
                        R.drawable.ic_contact, R.drawable.ic_offer, R.drawable.ic_news, R.drawable.ic_location,
                        R.drawable.ic_questionnaire, R.drawable.ic_organisers};
    private static final Integer[] idMainIcons= {R.drawable.ic_main_home, R.drawable.ic_main_agenda,
            R.drawable.ic_main_sponsors, R.drawable.ic_main_contact, R.drawable.ic_main_offer,
            R.drawable.ic_main_news, R.drawable.ic_main_location, R.drawable.ic_main_questionnaire,
            R.drawable.ic_main_organiser};

    public static Boolean isTag(String tag) {
        for (String tag1 : tags) {
            if (tag.equals(tag1)) {
                return true;
            }
        }
        return false;
    }

    public static Integer getTag(String tag) {
        for(int i=0; i<tags.length;  i++){
            if(tag.equals(tags[i])) {
                return idStrings[i];
            }
        }
        return R.string.exception_default;
    }

    public static Integer getIcon(String tag) {
        for(int i=0; i<tags.length;  i++){
            if(tag.equals(tags[i])) {
                return idIcons[i];
            }
        }
        return R.drawable.ic_navigation_item;
    }

    public static Integer getMainIcon(String tag) {
        for(int i=0; i<tags.length;  i++){
            if(tag.equals(tags[i])) {
                return idMainIcons[i];
            }
        }
        return R.drawable.ic_navigation_item;
    }
}
