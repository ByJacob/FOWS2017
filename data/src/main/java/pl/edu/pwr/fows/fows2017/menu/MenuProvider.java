package pl.edu.pwr.fows.fows2017.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.edu.pwr.fows.fows2017.base.OkHttpProvider;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.parser.JsonParserMenu;

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

    public List<Menu> constructDefaultMenu() {
        menus.clear();
        menus.add(Menu.Builder.aMenu().withId(1).withTag("HOME").build());
        menus.add(Menu.Builder.aMenu().withId(2).withTag("NEWS").build());
        menus.add(Menu.Builder.aMenu().withId(3).withTag("AGENDA").build());
        menus.add(Menu.Builder.aMenu().withId(4).withTag("SPONSORS").build());
        menus.add(Menu.Builder.aMenu().withId(5).withTag("CONTACT").build());
        menus.add(Menu.Builder.aMenu().withId(6).withTag("OFFER").build());
        menus.add(Menu.Builder.aMenu().withId(7).withTag("LOCATION").build());
        return menus;
    }

    private void getDate() throws IOException {
        String response = run(url);
        List<Menu> menusTmp = JsonParserMenu.parseJson(response);
        menus.clear();
        menus.addAll(menusTmp);
    }
}
