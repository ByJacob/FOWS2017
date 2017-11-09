package pl.edu.pwr.fows.fows2017.map;

import pl.edu.pwr.fows.fows2017.R;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class SnackBarMessageMap {
    private static final String[] tags = {"NETWORK", "BROWSER", "MAP", "DISABLE", "SEND",
            "QUESTIONNAIRE_DONE", "INCOMPLETE", "PASS_NOT_EQUAL", "FAIL_EMAIL", "ADD_ACCOUNT", "LOGIN",
            "UPDATE_SUCCESS", "UPDATE_FAIL", "SIGN_OUT_SUCCESS", "SIGN_OUT_FAIL", "LOGIN_FAIL"};
    private static final Integer[] textsId = {R.string.exception_network, R.string.exception_browser,
            R.string.exception_map, R.string.exception_disable, R.string.message_send,
            R.string.message_questionnaire_done, R.string.exception_incomplete,
            R.string.exception_pass_not_equal, R.string.exception_fail_email, R.string.message_add_account,
            R.string.message_login_success,R.string.message_update_success, R.string.exception_update_fail, R.string.signOutSuccess, R.string.signOutFaill,
            R.string.exception_login_fail};

    public static Integer getTag(String tag) {
        for(int i=0; i<tags.length;  i++){
            if(tag.equals(tags[i])) {
                return textsId[i];
            }
        }
        return R.string.exception_default;
    }
}
