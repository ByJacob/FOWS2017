package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentSponsorModule;
import pl.edu.pwr.fows.fows2017.fragment.FragmentSponsor;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 10.08.2017.
 */

@Subcomponent(modules = FragmentSponsorModule.class)
public interface FragmentSponsorComponent {
    void inject(FragmentSponsor fragmentSponsor);
}
