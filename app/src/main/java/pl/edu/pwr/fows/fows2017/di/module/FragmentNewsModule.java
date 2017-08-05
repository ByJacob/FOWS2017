package pl.edu.pwr.fows.fows2017.di.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.presenter.FragmentNewsPresenter;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

@Module
public class FragmentNewsModule {

    @Provides
    FragmentNewsPresenter provideFragmentNewsPresenter(UseCaseFactory factory){
        return new FragmentNewsPresenter(factory);
    }
}
