package pl.edu.pwr.fows.fows2017.gateway;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public interface FacebookPostGateway {

    List<FacebookPost> getPosts() throws Exception;
}
