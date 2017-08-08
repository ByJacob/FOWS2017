package pl.edu.pwr.fows.fows2017.sharedPreferencesAPI;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.gateway.FacebookPostGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 08.08.2017.
 */

public class SharedPreferencesAPIClient implements FacebookPostGateway{

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
}
