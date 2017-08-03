package pl.edu.pwr.fows.fows2017.di.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.presenter.FragmentHomePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 02.08.2017.
 */

@Module
public class FragmentHomeModule {

    @Provides
    FragmentHomePresenter provideFragmentHomePresenter(UseCaseFactory factory, BaseActivityView baseActivityView){
        return new FragmentHomePresenter(factory, baseActivityView);
    }
}
