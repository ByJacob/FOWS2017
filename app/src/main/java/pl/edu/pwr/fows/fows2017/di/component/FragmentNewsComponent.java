package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentNewsModule;
import pl.edu.pwr.fows.fows2017.fragment.FragmentNews;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

@Subcomponent(modules = FragmentNewsModule.class)
public interface FragmentNewsComponent {
    void inject(FragmentNews fragmentNews);
}
