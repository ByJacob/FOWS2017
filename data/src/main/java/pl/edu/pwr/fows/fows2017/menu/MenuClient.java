package pl.edu.pwr.fows.fows2017.menu;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class MenuClient implements MenuGateway {

    private final MenuProvider provider;

    @Inject
    public MenuClient() {
        provider = new MenuProvider();
    }

    @Override
    public Single<List<Menu>> getMenus() {
        return Single.just(provider.getMenus());
    }

    @Override
    public Single<Menu> getMenu(int position) {
        return Single.just(provider.getMenus().get(position));
    }
}
