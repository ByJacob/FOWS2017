package pl.edu.pwr.fows.fows2017.menu;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.entity.Menu;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

class MenuProvider extends OkHttpProvider {

    private final List<Menu> menus = new ArrayList<>();
    private final String url;

    public MenuProvider(String url) {
        this.url = url;
    }

    public List<Menu> getMenus() throws IOException {
        getDate();
        return menus;
    }

    public Menu getMenu(String tag) throws IOException {
        getDate();
        for (Menu menu : menus) {
            if (Objects.equals(menu.getTag(), tag))
                return menu;
        }
        return Menu.Builder.aMenu().withTag("ERROR").build();
    }

    public Menu getMenuDefaultConstruct(String tag) {
        constructDefaultMenu();
        for (Menu menu : menus) {
            if (Objects.equals(menu.getTag(), tag))
                return menu;
        }
        return Menu.Builder.aMenu().withTag("ERROR").build();
    }

    public List<Menu> constructDefaultMenu() {
        menus.clear();
        menus.add(Menu.Builder.aMenu().withPosition(1).withTag("HOME").build());
        menus.add(Menu.Builder.aMenu().withPosition(2).withTag("NEWS").build());
        menus.add(Menu.Builder.aMenu().withPosition(3).withTag("AGENDA").build());
        menus.add(Menu.Builder.aMenu().withPosition(4).withTag("SPONSORS").build());
        menus.add(Menu.Builder.aMenu().withPosition(5).withTag("CONTACT").build());
        menus.add(Menu.Builder.aMenu().withPosition(6).withTag("OFFER").build());
        menus.add(Menu.Builder.aMenu().withPosition(7).withTag("LOCATION").build());
        return menus;
    }

    private void getDate() throws IOException {
        final Gson gson = new Gson();
        Menu[] tmpMenus = gson.fromJson(run(url), Menu[].class);
        menus.clear();
        menus.addAll(Arrays.asList(tmpMenus));
    }
}
