package pl.edu.pwr.fows.fows2017.presenter;

import java.util.List;

import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.menu.MenuClient;
import pl.edu.pwr.fows.fows2017.usecase.MenuUseCase;
import pl.edu.pwr.fows.fows2017.view.DrawerMenuRowView;
import pl.edu.pwr.fows.fows2017.view.DrawerMenuView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class DrawerMenuPresenter {
    private static final Integer MAX_ALPHA_VALUE = 255;
    private static final Integer MAX_SLIDE_OFFSET_VALUE = 1;

    private final MenuUseCase useCase = new MenuUseCase(new MenuClient()); //todo remove data compile when add DI
    private List<Menu> menus;

    public DrawerMenuPresenter() {

    }
    public void listItemOnClick(DrawerMenuView view){
        view.closeDrawer();
    }
    public int getBackgroundToolbarAlpha(float slideOffset){
        return Math.round(MAX_ALPHA_VALUE*(MAX_SLIDE_OFFSET_VALUE-slideOffset/2));
    }

    public void onViewTaken() {
        menus = useCase.execute();
    }

    public int getMenusCount(){
        return menus.size();
    }

    public void configuremenuRow(DrawerMenuRowView view, int position){
        view.displayTitle(menus.get(position).getName());
        view.setIdFragment(menus.get(position).getName());
    }

}
