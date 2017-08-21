package pl.edu.pwr.fows.fows2017.gateway;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.Menu;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public interface MenuGateway {

    Observable<List<Menu>> getMenus();

    Observable<Menu> getMenu(String tag);


}
