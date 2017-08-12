package pl.edu.pwr.fows.fows2017.sharedPreferencesAPI;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.entity.Sponsor;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;
import pl.edu.pwr.fows.fows2017.gateway.SponsorGateway;
import pl.edu.pwr.fows.fows2017.interfave.SharedPreferencesDataInterface;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class SharedPreferencesAPIClient implements FacebookPostGateway, SponsorGateway {

    private SharedPreferencesAPIProvider provider;

    @Inject
    public SharedPreferencesAPIClient(SharedPreferencesDataInterface API){
        provider = new SharedPreferencesAPIProvider(API);
    }
    @Override
    public Observable<List<FacebookPost>> getPosts() {
        return null; //"Don't use"
    }

    @Override
    public Single<List<FacebookPost>> getPostsFromMemory() {
        return Single.just(provider.getFacebookPosts());
    }

    @Override
    public Observable<FacebookPost> getPost(Integer position) {
        return null;
    }

    @Override
    public Observable<List<List<Sponsor>>> getSponsors() {
        return null; //don't use
    }

    @Override
    public Single<List<List<Sponsor>>> getSponsorsFromMemory() {
        return Single.just(provider.getSponsors());
    }
}
