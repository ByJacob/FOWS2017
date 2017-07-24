package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.UseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class MenuUseCase implements UseCase<List<Menu>>{

    private final MenuGateway gateway;

    public MenuUseCase(MenuGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public List<Menu> execute() {
        return gateway.getMenus();
    }
}
