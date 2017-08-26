package pl.edu.pwr.fows.fows2017.gateway;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

public interface SponsorGateway {

    List<List<Sponsor>> getSponsors() throws Exception;
}
