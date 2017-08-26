package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentQuestionnaireModule;
import pl.edu.pwr.fows.fows2017.fragment.FragmentQuestionnaire;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 22.08.2017.
 */

@Subcomponent(modules = FragmentQuestionnaireModule.class)
public interface FragmentQuestionnaireComponent {
    void inject(FragmentQuestionnaire fragmentQuestionnaire);
}
