package pl.edu.pwr.fows.fows2017.presenter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import pl.edu.pwr.fows.fows2017.UseCaseFactory;
import pl.edu.pwr.fows.fows2017.entity.FacebookPost;
import pl.edu.pwr.fows.fows2017.view.FragmentNewsView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FragmentNewsPresenter {

    private final UseCaseFactory factory;
    private FragmentNewsView view;
    private List<FacebookPost> posts;

    public FragmentNewsPresenter(UseCaseFactory factory) {
        this.factory = factory;
    }

    public void onViewTaken(FragmentNewsView view){
        this.view = view;
        factory.getFacebookPosts().execute().subscribe(this::onFacebookPostsFetchSuccess);
    }

    private void onFacebookPostsFetchSuccess(List<FacebookPost> posts){
        this.posts = posts;
        view.disableLoading();
    }
    public int getPostsCount() {
        return posts.size();
    }

    public Object getPost(int i) {
        return posts.get(i);
    }

    public int getPostId(int i) {
        return posts.get(i).getId();
    }

    public String getHeaderDate(int i, Locale locale) {
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy HH:mm", locale);
        return df.format(posts.get(i).getCreatedTime());
    }

    public String getMessage(int i) {
        return posts.get(i).getMessage();
    }
}
