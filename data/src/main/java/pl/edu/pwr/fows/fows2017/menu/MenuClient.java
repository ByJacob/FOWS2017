package pl.edu.pwr.fows.fows2017.menu;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class MenuClient implements MenuGateway {

    private final MenuProvider provider;
    private final static String URL = "http://fows.pwr.edu.pl/sections/android-menu.php?android";

    @Inject
    public MenuClient() {
        provider = new MenuProvider(URL);
    }

    @Override
    public Observable<List<Menu>> getMenus() {
        return Observable.fromCallable(() -> {
            try {
                return provider.getMenus();
            } catch (Exception e) {
                return provider.constructDefaultMenu();
            }
        });
    }

    @Override
    public Observable<Menu> getMenu(String tag) {
        return Observable.fromCallable(() -> provider.getMenu(tag));
    }
}
