package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Component;
import pl.edu.pwr.fows.fows2017.activity.BaseActivity;
import pl.edu.pwr.fows.fows2017.di.module.ActivityModule;
import pl.edu.pwr.fows.fows2017.di.module.FragmentAgendaModule;
import pl.edu.pwr.fows.fows2017.di.module.FragmentContactModule;
import pl.edu.pwr.fows.fows2017.di.module.FragmentCreateAccountModule;
import pl.edu.pwr.fows.fows2017.di.module.FragmentHomeModule;
import pl.edu.pwr.fows.fows2017.di.module.FragmentNewsModule;
import pl.edu.pwr.fows.fows2017.di.module.FragmentOrganiserModule;
import pl.edu.pwr.fows.fows2017.di.module.FragmentQuestionnaireModule;
import pl.edu.pwr.fows.fows2017.di.module.FragmentSponsorModule;
import pl.edu.pwr.fows.fows2017.di.scope.ActivityScope;
import pl.edu.pwr.fows.fows2017.fragment.FragmentQuestionnaire;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

    FragmentHomeComponent addModule(FragmentHomeModule module);

    FragmentNewsComponent addModule(FragmentNewsModule module);

    FragmentSponsorComponent addModule(FragmentSponsorModule module);

    FragmentAgendaComponent addModule(FragmentAgendaModule module);

    FragmentContactComponent addModule(FragmentContactModule module);

    FragmentQuestionnaireComponent addModule(FragmentQuestionnaireModule module);

    FragmentOrganiserComponent addModule(FragmentOrganiserModule module);

    FragmentCreateAccountComponent addModule(FragmentCreateAccountModule module);
}
