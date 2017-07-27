package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.activity.MainMenu;
import pl.edu.pwr.fows.fows2017.di.module.MainMenuModule;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */
@Subcomponent(modules = MainMenuModule.class)
public interface MainMenuComponent {
    void inject(MainMenu mainMenu);
}
