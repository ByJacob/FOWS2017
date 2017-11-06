package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentLoginModule;
import pl.edu.pwr.fows.fows2017.fragment.FragmentLogin;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 06.11.2017.
 */

@Subcomponent(modules = FragmentLoginModule.class)
public interface FragmentLoginComponent {
    void inject(FragmentLogin fragmentLogin);
}
