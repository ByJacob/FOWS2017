package pl.edu.pwr.fows.fows2017.presenter;

import java.util.List;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.DrawerMenuRowView;
import pl.edu.pwr.fows.fows2017.view.DrawerMenuView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class DrawerMenuPresenter {
    private static final Integer MAX_ALPHA_VALUE = 255;
    private static final Integer MAX_SLIDE_OFFSET_VALUE = 1;

    private Long lastTimestampRefresh;
    private String actualFragmentTag;

    private final UseCaseFactory factory;
    private final BaseActivityView baseActivityView;
    private List<Menu> menus;

    public DrawerMenuPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        this.factory = factory;
        this.baseActivityView = baseActivityView;
        lastTimestampRefresh = 0L;
    }

    public void menuItemClick(DrawerMenuView view, int position, String tag) {
        view.closeDrawer();
        baseActivityView.blockContainerClick(false);
        baseActivityView.changeMainFragment(tag);
    }

    public int getBackgroundToolbarAlpha(float slideOffset) {
        return Math.round(MAX_ALPHA_VALUE * (MAX_SLIDE_OFFSET_VALUE - slideOffset / 2));
    }

    public void onViewTaken() {
        factory.getMenuUseCase().execute().subscribe(this::onMenusListFetchSuccess);
    }

    public int getMenusCount() {
        return menus.size();
    }

    public void configureMenuRow(DrawerMenuRowView view, int position) {
        view.displayTitle(menus.get(position).getTag());
        view.setIdFragment(menus.get(position).getTag());
        if (menus.get(position).getTag().equals(actualFragmentTag)) {
            view.setIconToActive(true);
        } else {
            view.setIconToActive(false);
        }
        view.setTag(menus.get(position).getTag());
    }

    private void onMenusListFetchSuccess(List<Menu> menus) {
        this.menus = menus;
    }

    public void menuDrawerSlide(DrawerMenuView fragment) {
        if (System.currentTimeMillis() - 100L > lastTimestampRefresh) {
            lastTimestampRefresh = System.currentTimeMillis();
            fragment.refreshMenuIcon();
        }
        baseActivityView.blockContainerClick(true);
    }

    public void menuDrawerClose() {
        baseActivityView.blockContainerClick(false);
    }

    public void setActualFragmentTag(String actualFragmentTag) {
        this.actualFragmentTag = actualFragmentTag;
    }


}
