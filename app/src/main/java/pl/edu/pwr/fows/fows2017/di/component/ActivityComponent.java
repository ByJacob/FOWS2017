package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Component;
import pl.edu.pwr.fows.fows2017.di.module.ActivityModule;
import pl.edu.pwr.fows.fows2017.di.module.MainMenuModule;
import pl.edu.pwr.fows.fows2017.di.scope.ActivityScope;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    MainMenuComponent addModule(MainMenuModule module);
}
