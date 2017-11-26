package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentContestModule;
import pl.edu.pwr.fows.fows2017.fragment.FragmentContest;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 26.11.2017.
 */

@Subcomponent(modules = FragmentContestModule.class)
public interface FragmentContestComponent {
    void inject(FragmentContest fragmentContest);
}
