package pl.edu.pwr.fows.fows2017.di.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.presenter.FragmentOrganiserPresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 16.10.2017.
 */

@Module
public class FragmentOrganiserModule {

    @Provides
    FragmentOrganiserPresenter provideFragmentOrganiserPresenter(UseCaseFactory factory, BaseActivityView baseActivityView){
        return new FragmentOrganiserPresenter(factory, baseActivityView);
    }
}
