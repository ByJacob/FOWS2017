package pl.edu.pwr.fows.fows2017.sponsors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.interfave.SharedPreferencesDataInterface;
import pl.edu.pwr.fows.fows2017.parser.JsonParserSponsor;
import pl.edu.pwr.fows.fows2017.sharedPreferencesAPI.SharedPreferencesAPIProvider;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class SponsorsProvider extends OkHttpProvider{

    final private List<List<Sponsor>> sponsors = new ArrayList<>();
    private final String url;
    private final SharedPreferencesDataInterface sharedPreferences;

    public SponsorsProvider(String url, SharedPreferencesDataInterface sharedPreferences) {
        this.url = url;
        this.sharedPreferences = sharedPreferences;
    }

    public List<List<Sponsor>> getSponsors() throws Exception {
        if (sponsors.size() < 1) {
            getData();
        }
        return sponsors;
    }

    private void getData() throws Exception {
        String response;
        if (sponsors.size() < 1) {
            response = run(url);
            sharedPreferences.save(SharedPreferencesAPIProvider.TAG_SPONSORS, response);
            List<List<Sponsor>> sponsorsTMP = JsonParserSponsor.parseJson(response);
            for(int i=0; i<sponsorsTMP.size(); i++){
                sponsors.add(i, new ArrayList<>());
                for(int j=0; j<sponsorsTMP.get(i).size(); j++)
                    sponsors.get(i).add(sponsorsTMP.get(i).get(j));
            }
        }
    }
}
