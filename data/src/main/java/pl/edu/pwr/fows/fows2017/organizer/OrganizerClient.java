package pl.edu.pwr.fows.fows2017.organizer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.Organizer;
import pl.edu.pwr.fows.fows2017.gateway.OrganizerGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

public class OrganizerClient implements OrganizerGateway {

    private OrganizerProvider provider;

    @Inject
    public OrganizerClient() {
        this.provider = new OrganizerProvider();
    }

    @Override
    public Single<List<Organizer>> getOrganizers() {
        return Single.just(provider.getOrganizers());
    }
}
