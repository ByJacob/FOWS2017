package pl.edu.pwr.fows.fows2017.di.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.presenter.FragmentContestPresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.11.2017.
 */

@Module
public class FragmentContestModule {
    @Provides
    FragmentContestPresenter provideFragmentContestPresenter(UseCaseFactory factory, BaseActivityView baseActivityView){
        return new FragmentContestPresenter(factory, baseActivityView);
    }
}
