package pl.edu.pwr.fows.fows2017.organiser;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.entity.Organiser;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 16.10.2017.
 */

public class OrganiserProvider extends OkHttpProvider {

    private final String url;

    public OrganiserProvider(String url) {
        this.url = url;
    }

    public List<Organiser> getOrganisers() throws IOException {
        final Gson gson = new Gson();
        Organiser[] organisers = gson.fromJson(run(url), Organiser[].class);
        return Arrays.asList(organisers);
    }
}
