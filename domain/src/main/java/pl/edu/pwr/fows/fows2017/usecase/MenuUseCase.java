package pl.edu.pwr.fows.fows2017.usecase;

import java.util.List;

import io.reactivex.Single;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxSingleUseCase;
import pl.edu.pwr.fows.fows2017.usecase.base.UseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class MenuUseCase extends AbstractRxSingleUseCase<List<Menu>> {

    private final MenuGateway gateway;

    public MenuUseCase(FowsRxTransformerProvider rxTransformer, MenuGateway gateway) {
        super(rxTransformer);
        this.gateway = gateway;
    }

    @Override
    protected Single<List<Menu>> createSingle() {
        return gateway.getMenus();
    }
}
