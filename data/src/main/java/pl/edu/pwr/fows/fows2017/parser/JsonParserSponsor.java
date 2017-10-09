package pl.edu.pwr.fows.fows2017.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.Sponsor;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class JsonParserSponsor {

    public static List<List<Sponsor>> parseJson(String json){
        List<List<Sponsor>> response = new ArrayList<>();
        JSONObject jsonObj = new JSONObject(json);
        Iterator<String> keys = jsonObj.keys();
        for(int i=0; i<jsonObj.length(); i++) {
            JSONArray data0 = jsonObj.getJSONArray(keys.next());
            response.add(i, new ArrayList<>());
            for (int j = 0; j < data0.length(); j++) {
                JSONObject sponsor = data0.getJSONObject(j);
                String file = sponsor.getString("file");
                String name = sponsor.getString("name");
                response.get(i).add(Sponsor.Builder.aSponsor().withName(name).withUrl(file).build());
            }
        }
        return response;
    }
}
