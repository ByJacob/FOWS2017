package pl.edu.pwr.fows.fows2017.map;

import pl.edu.pwr.fows.fows2017.R;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class ExceptionMap {
    private static final String[] tags = {"NETWORK", "BROWSER"};
    private static final Integer[] textsId = {R.string.exception_network, R.string.exception_browser};

    public static Integer getTag(String tag) {
        for(int i=0; i<tags.length;  i++){
            if(tag.equals(tags[i])) {
                return textsId[i];
            }
        }
        return R.string.exception_default;
    }
}
