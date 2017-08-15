package pl.edu.pwr.fows.fows2017.di.component;

import dagger.Subcomponent;
import pl.edu.pwr.fows.fows2017.di.module.FragmentAgendaModule;
import pl.edu.pwr.fows.fows2017.fragment.FragmentAgenda;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

@Subcomponent(modules = FragmentAgendaModule.class)
public interface FragmentAgendaComponent {
    void inject(FragmentAgenda fragmentAgenda);
}
