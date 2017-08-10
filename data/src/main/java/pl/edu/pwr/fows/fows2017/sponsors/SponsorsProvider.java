package pl.edu.pwr.fows.fows2017.sponsors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.parser.JsonParserSponsor;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public class SponsorsProvider {

    final private List<List<Sponsor>> sponsors = new ArrayList<>();
    private String url;
    private OkHttpClient client;

    public SponsorsProvider(String url) {
        this.url = url;
        this.client = new OkHttpClient();
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
            //gatewaySharedPref.save(SharedPreferencesAPIProvider.TAG_FACEBOOK_POSTS, response);
            List<List<Sponsor>> sponsorsTMP = JsonParserSponsor.parseJson(response);
            for(int i=0; i<sponsorsTMP.size(); i++){
                sponsors.add(i, new ArrayList<>());
                for(int j=0; j<sponsorsTMP.get(i).size(); j++)
                    sponsors.get(i).add(sponsorsTMP.get(i).get(j));
            }
        }
    }

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
