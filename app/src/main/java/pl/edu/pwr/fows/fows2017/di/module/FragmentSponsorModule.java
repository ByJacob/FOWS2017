package pl.edu.pwr.fows.fows2017.di.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.presenter.FragmentSponsorPresenter;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

@Module
public class FragmentSponsorModule {

    @Provides
    FragmentSponsorPresenter provideFragmentSponsorPresenter(UseCaseFactory factory){
        return new FragmentSponsorPresenter(factory);
    }
}
