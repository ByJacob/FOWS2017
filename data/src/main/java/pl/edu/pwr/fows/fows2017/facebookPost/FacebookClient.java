package pl.edu.pwr.fows.fows2017.facebookPost;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FacebookClient implements FacebookPostGateway {

    private FacebookProvider provider;

    @Inject
    public FacebookClient() {
        provider = new FacebookProvider();
    }

    @Override
    public Single<List<FacebookPost>> getPosts() {
        return Single.just(provider.getPosts());
    }

    @Override
    public Single<FacebookPost> getPost(Integer position) {
        return null;
    }
}
