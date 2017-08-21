package pl.edu.pwr.fows.fows2017.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import pl.edu.pwr.fows.fows2017.entity.Menu;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 21.08.2017.
 */

public class JsonParserMenu {
    
    public static List<Menu> parseJson(String json) {
        List<Menu> response = new ArrayList<>();
        JSONObject object = new JSONObject(json);
        Iterator<String> keys = object.keys();
        while(keys.hasNext()) {
            String tag = keys.next();
            if (Objects.equals(object.getString(tag), "ON"))
                response.add(Menu.Builder.aMenu().withTag(tag).build());
        }

        return response;
    }
}
