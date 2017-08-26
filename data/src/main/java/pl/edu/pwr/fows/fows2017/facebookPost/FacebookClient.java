package pl.edu.pwr.fows.fows2017.facebookPost;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.declarationInterface.SharedPreferencesDataInterface;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FacebookClient implements FacebookPostGateway {

    private static final String URL = "http://fows.pwr.edu.pl/cache/android.php";
    private final FacebookProvider provider;

    @Inject
    public FacebookClient(SharedPreferencesDataInterface gatewaySharedPref) {
        provider = new FacebookProvider(URL, gatewaySharedPref);
    }

    @Override
    public List<FacebookPost> getPosts() throws Exception {
        return provider.getPosts();
    }
}
