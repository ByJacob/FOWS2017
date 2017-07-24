package pl.edu.pwr.fows.fows2017.menu;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.Menu;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class MenuProvider {

    private final List<Menu> menus = new ArrayList<>();

    public MenuProvider() {
        //Todo Add download menu status with internet
        menus.add(Menu.Builder.aMenu().withId(1).withName("HOME").build());
        menus.add(Menu.Builder.aMenu().withId(2).withName("AGENDA").build());
        menus.add(Menu.Builder.aMenu().withId(3).withName("SPONSORS").build());
        menus.add(Menu.Builder.aMenu().withId(4).withName("CONTACT").build());
        menus.add(Menu.Builder.aMenu().withId(5).withName("OFFER").build());
    }

    public List<Menu> getMenus() {
        return menus;
    }
}
