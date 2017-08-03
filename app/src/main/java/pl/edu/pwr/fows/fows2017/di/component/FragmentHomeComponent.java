package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentHomeModule;
import pl.edu.pwr.fows.fows2017.fragment.FragmentHome;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 02.08.2017.
 */

@Subcomponent(modules = FragmentHomeModule.class)
public interface FragmentHomeComponent {
    void inject(FragmentHome fragmentHome);
}
