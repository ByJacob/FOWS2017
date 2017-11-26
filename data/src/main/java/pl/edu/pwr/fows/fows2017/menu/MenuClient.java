package pl.edu.pwr.fows.fows2017.menu;

import java.util.List;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class MenuClient implements MenuGateway {

    private final static String URL = "https://fows-2017.firebaseio.com/menu.json";
    private final MenuProvider provider;

    @Inject
    public MenuClient() {
        provider = new MenuProvider(URL);
    }

    @Override
    public List<Menu> getMenus() {
        try {
            return provider.getMenus();
        } catch (Exception e) {
            return provider.constructDefaultMenu();
        }
    }

    @Override
    public Menu getMenu(String tag) {
        try {
            return provider.getMenu(tag);
        } catch (Exception e) {
            return provider.getMenuDefaultConstruct(tag);
        }
    }
}
