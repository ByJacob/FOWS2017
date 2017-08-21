package pl.edu.pwr.fows.fows2017.usecase;

import io.reactivex.Observable;
import pl.edu.pwr.fows.fows2017.aux_data.FowsRxTransformerProvider;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.gateway.MenuGateway;
import pl.edu.pwr.fows.fows2017.usecase.base.AbstractRxObservableUseCase;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 21.08.2017.
 */

public class MenuWithTagUseCase extends AbstractRxObservableUseCase<Menu> {

    private MenuGateway gateway;
    private String tag;

    public MenuWithTagUseCase(FowsRxTransformerProvider rxTransformer, MenuGateway menuGateway, String tag) {
        super(rxTransformer);
        this.gateway = menuGateway;
        this.tag = tag;
    }

    @Override
    protected Observable<Menu> createObservable() {
        return gateway.getMenu(tag);
    }
}
