package pl.edu.pwr.fows.fows2017;

import javax.inject.Inject;

import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.usecase.MenuUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public class UseCaseFactory {

    MenuGateway menuGateway;

    @Inject
    public UseCaseFactory(MenuGateway menuGateway){
        this.menuGateway = menuGateway;
    }

    public MenuUseCase getMenuUseCase(){
        return new MenuUseCase(menuGateway);
    }
}
