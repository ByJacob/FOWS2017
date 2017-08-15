package pl.edu.pwr.fows.fows2017.di.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.presenter.FragmentAgendaPresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

@Module
public class FragmentAgendaModule {

    @Provides
    FragmentAgendaPresenter provideFragmentAgendaPresenter(UseCaseFactory factory, BaseActivityView baseActivityView){
        return new FragmentAgendaPresenter(factory, baseActivityView);
    }
}
