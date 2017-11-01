package pl.edu.pwr.fows.fows2017.di.component;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 16.10.2017.
 */

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentOrganiserModule;
import pl.edu.pwr.fows.fows2017.fragment.FragmentOrganiser;

@Subcomponent(modules = FragmentOrganiserModule.class)
public interface FragmentOrganiserComponent {
    void inject(FragmentOrganiser fragment);
}
