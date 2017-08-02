package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentMainModule;
import pl.edu.pwr.fows.fows2017.fragment.base.BaseFragment;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 02.08.2017.
 */

@Subcomponent(modules = FragmentMainModule.class)
public interface FragmentMainComponent {
    void inject(BaseFragment baseFragment);
}
