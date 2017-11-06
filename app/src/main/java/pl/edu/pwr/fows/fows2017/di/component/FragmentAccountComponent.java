package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentAccountModule;
import pl.edu.pwr.fows.fows2017.fragment.loginUser.FragmentAccount;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.11.2017.
 */

@Subcomponent(modules = FragmentAccountModule.class)
public interface FragmentAccountComponent {
    void inject(FragmentAccount fragmentAccount);
}
