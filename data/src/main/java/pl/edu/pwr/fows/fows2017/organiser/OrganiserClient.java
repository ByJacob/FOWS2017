package pl.edu.pwr.fows.fows2017.organiser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.OrganiserTeam;
import pl.edu.pwr.fows.fows2017.gateway.OrganiserGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 16.10.2017.
 */

public class OrganiserClient implements OrganiserGateway {

    private final static String URL = "http://fows.pwr.edu.pl/sections/android-organisers.php?android";
    private final OrganiserProvider provider;

    public OrganiserClient() {
        this.provider = new OrganiserProvider(URL);
    }

    @Override
    public List<OrganiserTeam> getOrganisers() {
        try {
            return provider.getOrganisers();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
