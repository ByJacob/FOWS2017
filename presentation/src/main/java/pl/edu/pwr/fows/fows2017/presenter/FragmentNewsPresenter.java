package pl.edu.pwr.fows.fows2017.presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.presenter.base.BasePresenter;
import pl.edu.pwr.fows.fows2017.view.BaseActivityView;
import pl.edu.pwr.fows.fows2017.view.FragmentNewsRowView;
import pl.edu.pwr.fows.fows2017.view.FragmentNewsView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FragmentNewsPresenter extends BasePresenter<FragmentNewsView>{

    private FragmentNewsView view;
    private List<FacebookPost> posts;
    private BaseActivityView baseActivityView;
    private Boolean isNetwork;

    public FragmentNewsPresenter(UseCaseFactory factory, BaseActivityView baseActivityView) {
        super(factory);
        this.baseActivityView = baseActivityView;
    }

    public void onViewTaken(FragmentNewsView view){
        this.view = view;
        super.factory.getFacebookPosts().execute().subscribe(this::onFacebookPostsFetchSuccess,
                this::onFacebookPostsFetchFail);
    }

    private void onFacebookPostsFetchSuccess(List<FacebookPost> posts){
        this.posts = posts;
        isNetwork = true;
        view.disableLoading();
    }

    private void onFacebookPostsFetchFail(Throwable throwable) {
        isNetwork = false;
        super.factory.getFacebookPostsSharedPref().execute().subscribe(this::onFacebookPostsFromMemorySuccess);
    }

    private void onFacebookPostsFromMemorySuccess(List<FacebookPost> posts) {
        this.posts = posts;
        if(getPostsCount()<1)
            baseActivityView.showOnError("NETWORK", true);
        else
            baseActivityView.showOnError("NETWORK", false);
        view.disableLoading();
    }

    public int getPostsCount() {
        return posts.size();
    }

    public void configureNewsRow(FragmentNewsRowView holder, int position, Locale locale) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy HH:mm", locale);
        holder.setDate(df.format(posts.get(position).getCreatedTime()));
        holder.setMessage(posts.get(position).getMessage());
        if(isNetwork)
            holder.setPicture(posts.get(position).getPicture(), position);
    }

    public boolean onMessageClick(String clickLine, FragmentNewsRowView rowView) {
        String [] splits = clickLine.split(" ");
        for (String split : splits) {
            if (split.contains("@")) {
                rowView.openAppSendEmail(split);
                return true;
            }
        }
        return false;
    }

    public void onPictureClick(Integer position, FragmentNewsRowView rowView) {
        rowView.openFacebook(posts.get(position).getLink());
    }
}
