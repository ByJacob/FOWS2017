package pl.edu.pwr.fows.fows2017.presenter;

import java.util.List;
import java.util.Locale;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.Menu;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.DrawerMenuView;
import pl.edu.pwr.fows.fows2017.view.row.DrawerMenuRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 24.07.2017.
 */

public class DrawerMenuPresenter extends BasePresenter<DrawerMenuView> {
    private static final Integer MAX_ALPHA_VALUE = 255;
    private static final Integer MAX_SLIDE_OFFSET_VALUE = 1;

    private static final String LOCATION_D20_ID = "D-20, Janiszewskiego, 52-007 Wroc\u0142aw, Polska";

    private Long lastTimestampRefresh;
    private String actualFragmentTag;
    private List<Menu> menus;

    public DrawerMenuPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
        this.baseActivityView = baseActivityView;
        lastTimestampRefresh = 0L;
        actualFragmentTag = "";
    }

    @Override
    public void onViewTaken(DrawerMenuView view) {
        baseActivityView.enableLoadingBar();
        super.factory.getMenuUseCase().execute().subscribe(this::onMenusListFetchSuccess);
        this.view = view;
    }

    public void changeMenusInDatabase() {
        super.factory.getMenuUseCase().execute().subscribe(this::onMenusChangeListFetchSuccess);
    }

    private void onMenusChangeListFetchSuccess(List<Menu> menus) {
        this.menus = menus;
    }

    public void openFragment(String tag) {
        if (view != null)
            view.closeDrawer();
        baseActivityView.blockContainerClick(false);
        baseActivityView.changeMainFragment(tag);
    }

    public int getBackgroundToolbarAlpha(float slideOffset) {
        return Math.round(MAX_ALPHA_VALUE * (MAX_SLIDE_OFFSET_VALUE - slideOffset / 2));
    }

    public int getMenusCount() {
        return menus.size();
    }

    public void configureMenuRow(DrawerMenuRowView view, int position) {
        view.displayTitle(menus.get(position).getTag());
        if (menus.get(position).getTag().equals(actualFragmentTag)) {
            view.setIconToActive(true);
        } else {
            view.setIconToActive(false);
        }
        view.setTag(menus.get(position).getTag());
    }

    public void menuDrawerSlide(DrawerMenuView fragment) {
        if (System.currentTimeMillis() - 1000L > lastTimestampRefresh) {
            lastTimestampRefresh = System.currentTimeMillis();
            fragment.refreshMenuIcon();
        }
        baseActivityView.blockContainerClick(true);
    }

    public void menuDrawerClose() {
        baseActivityView.blockContainerClick(false);
    }

    public String getActualFragmentTag() {
        return actualFragmentTag;
    }

    public void setActualFragmentTag(String actualFragmentTag) {
        this.actualFragmentTag = actualFragmentTag;
    }

    private void onMenusListFetchSuccess(List<Menu> menus) {
        this.menus = menus;
        baseActivityView.disableLoadingBar();
        baseActivityView.continueLoading();
        view.continueLoading();
    }

    public void clickOffer(Locale locale) {
        factory.getOfferUrl(locale).execute().subscribe(this::fetchSuccessOfferUrl);
    }

    private void fetchSuccessOfferUrl(String url) {
        String[] urls = url.split(";");
        baseActivityView.startBrowser(urls[0], urls[1]);
    }

    public void clickLocation() {
        baseActivityView.startMaps(LOCATION_D20_ID.replace(",", "%2C").replace(" ", "+"));
    }

    public void sendToken(String token, Locale locale) {
        factory.sendFirebaseToken(token, locale.getLanguage()).execute().subscribe(this::onSendSuccess, this::onSendFail);
    }

    private void onSendFail(Throwable throwable) {
        throwable.printStackTrace();
    }

    private void onSendSuccess(Boolean isNewToken) {
        if (isNewToken)
            baseActivityView.sendLogEvent();
    }
}
