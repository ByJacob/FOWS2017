package pl.edu.pwr.fows.fows2017;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.usecase.MenuUseCase;
import pl.edu.pwr.fows.fows2017.usecase.base.UseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 27.07.2017.
 */

public class UseCaseFactory {

    private final MenuGateway menuGateway;
    private final FowsRxTransformerProvider rxTransformer;

    @Inject
    public UseCaseFactory(FowsRxTransformerProvider rxTransformer, MenuGateway menuGateway){
        this.menuGateway = menuGateway;
        this.rxTransformer = rxTransformer;
    }

    public UseCase<Single<List<Menu>>> getMenuUseCase(){
        return new MenuUseCase(rxTransformer, menuGateway);
    }
}
