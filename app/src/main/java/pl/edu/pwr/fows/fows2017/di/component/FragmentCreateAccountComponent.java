package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentCreateAccountModule;
import pl.edu.pwr.fows.fows2017.fragment.FragmentCreateAccount;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.11.2017.
 */

@Subcomponent(modules = FragmentCreateAccountModule.class)
public interface FragmentCreateAccountComponent {
    void inject(FragmentCreateAccount fragmentCreateAccount);
}
