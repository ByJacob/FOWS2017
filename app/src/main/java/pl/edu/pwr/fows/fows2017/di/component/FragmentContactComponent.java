package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentContactModule;
import pl.edu.pwr.fows.fows2017.fragment.FragmentContact;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 17.08.2017.
 */

@Subcomponent(modules = FragmentContactModule.class)
public interface FragmentContactComponent {
    void inject(FragmentContact fragmentContact);
}
