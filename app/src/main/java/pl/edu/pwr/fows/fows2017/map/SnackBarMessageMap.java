package pl.edu.pwr.fows.fows2017.map;

import pl.edu.pwr.fows.fows2017.R;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class SnackBarMessageMap {
    private static final String[] tags = {"NETWORK", "BROWSER", "MAP", "DISABLE", "SEND"};
    private static final Integer[] textsId = {R.string.exception_network, R.string.exception_browser,
            R.string.exception_map, R.string.exception_disable, R.string.message_send};

    public static Integer getTag(String tag) {
        for(int i=0; i<tags.length;  i++){
            if(tag.equals(tags[i])) {
                return textsId[i];
            }
        }
        return R.string.exception_default;
    }
}
