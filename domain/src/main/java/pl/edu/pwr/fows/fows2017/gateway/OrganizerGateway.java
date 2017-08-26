package pl.edu.pwr.fows.fows2017.gateway;

import java.util.List;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.Organizer;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public interface OrganizerGateway {

    List<Organizer> getOrganizers();
}
