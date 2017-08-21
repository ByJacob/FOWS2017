package pl.edu.pwr.fows.fows2017.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    private Boolean isNetwork;

    public MenuProvider(String url) {
        this.url = url;
        isNetwork = true;

    }

    public List<Menu> getMenus() throws IOException {
        if(menus.size()<1 || !isNetwork ){
            String response = run(url);
            List<Menu> menusTmp = JsonParserMenu.parseJson(response);
            menus.addAll(menusTmp);
        }
        return menus;
    }

    public Menu getMenu(String tag) {
        return null;
    }

    public List<Menu> constructDefaultMenu(){
        menus.clear();
        isNetwork = false;
        menus.add(Menu.Builder.aMenu().withId(1).withTag("HOME").build());
        menus.add(Menu.Builder.aMenu().withId(2).withTag("NEWS").build());
        menus.add(Menu.Builder.aMenu().withId(3).withTag("AGENDA").build());
        menus.add(Menu.Builder.aMenu().withId(4).withTag("SPONSORS").build());
        menus.add(Menu.Builder.aMenu().withId(5).withTag("CONTACT").build());
        menus.add(Menu.Builder.aMenu().withId(6).withTag("OFFER").build());
        menus.add(Menu.Builder.aMenu().withId(7).withTag("LOCATION").build());
        return menus;
    }
}
