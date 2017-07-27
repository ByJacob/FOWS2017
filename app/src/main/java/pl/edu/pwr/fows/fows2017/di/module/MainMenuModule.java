package pl.edu.pwr.fows.fows2017.di.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.presenter.DrawerMenuPresenter;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */
@Module
public class MainMenuModule {

    @Provides
    DrawerMenuPresenter getDrawerMenuPresenter(UseCaseFactory useCaseFactory) {
        return new DrawerMenuPresenter(useCaseFactory);
    }
}
