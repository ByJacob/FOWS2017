package pl.edu.pwr.fows.fows2017.presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentNewsView;
import pl.edu.pwr.fows.fows2017.view.row.FragmentNewsRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FragmentNewsPresenter extends BasePresenter<FragmentNewsView> {

    private List<FacebookPost> posts;
    private List<Boolean> isSowedPosts = new ArrayList<>();
    private Boolean isNetwork;

    public FragmentNewsPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory, baseActivityView);
    }

    public void onViewTaken(FragmentNewsView view) {
        this.view = view;
        baseActivityView.enableLoadingBar();
        super.factory.getFacebookPosts().execute().subscribe(this::onFacebookPostsFetchSuccess,
                this::onFacebookPostsFetchFail);
    }

    private void onFacebookPostsFetchSuccess(List<FacebookPost> posts) {
        this.posts = posts;
        for (int i = 0; i < posts.size(); i++)
            isSowedPosts.add(false);
        isNetwork = true;
        baseActivityView.disableLoadingBar();
        view.continueLoading();
    }

    @SuppressWarnings("UnusedParameters")
    private void onFacebookPostsFetchFail(Throwable throwable) {
        if (throwable.getMessage() != null)
            if (throwable.getMessage().contains("No address")) {
                isNetwork = false;
                super.factory.getFacebookPostsSharedPref().execute().subscribe(this::onFacebookPostsFromMemorySuccess);
            }
    }

    private void onFacebookPostsFromMemorySuccess(List<FacebookPost> posts) {
        this.posts = posts;
        for (int i = 0; i < posts.size(); i++)
            isSowedPosts.add(false);
        if (getPostsCount() < 1)
            baseActivityView.showMessage("NETWORK", true);
        else
            baseActivityView.showMessage("NETWORK", false);
        baseActivityView.disableLoadingBar();
        view.continueLoading();
    }

    public int getPostsCount() {
        return posts.size();
    }

    public void configureNewsRow(FragmentNewsRowView holder, int position, Locale locale) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy HH:mm", locale);
        holder.setDate(df.format(posts.get(position).getCreatedTime()));
        holder.setMessage(posts.get(position).getMessage());
        if (isNetwork)
            holder.setPicture(posts.get(position).getPicture(), position);
        if (!isSowedPosts.get(position)) {
            view.firebaseLogPost(posts.get(position).getId(), false);
            isSowedPosts.set(position, true);
        }
    }

    public boolean onMessageClick(String clickLine, FragmentNewsRowView rowView) {
        String[] splits = clickLine.split(" ");
        for (String split : splits) {
            if (split.contains("@")) {
                rowView.openAppSendEmail(split);
                return true;
            }
        }
        return false;
    }

    public void onPictureClick(Integer position, FragmentNewsRowView rowView) {
        rowView.openFacebook("https://www.facebook.com/WirelessGroup/posts/" + posts.get(position).getId());
        view.firebaseLogPost(posts.get(position).getId(), true);
    }
}
