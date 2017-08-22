package pl.edu.pwr.fows.fows2017.di.module;

import dagger.Module;
import dagger.Provides;
import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.presenter.FragmentQuestionnairePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

@Module
public class FragmentQuestionnaireModule {

    @Provides
    FragmentQuestionnairePresenter providePresenter(UseCaseFactory factory, BaseActivityView baseActivityView){
        return new FragmentQuestionnairePresenter(factory, baseActivityView);
    }
}
