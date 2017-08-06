package pl.edu.pwr.fows.fows2017.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.edu.pwr.fows.fows2017.R;
import pl.edu.pwr.fows.fows2017.presenter.FragmentNewsPresenter;
import pl.edu.pwr.fows.fows2017.view.FragmentNewsRowView;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 05.08.2017.
 */

public class FragmentNewsAdapter extends RecyclerView.Adapter<FragmentNewsAdapter.NewsAdapter> {

    private FragmentNewsPresenter presenter;
    private Context context;
    private LayoutInflater inflater;

    public FragmentNewsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setPresenter(FragmentNewsPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public NewsAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_fragment_news_item, parent, false);
        return new NewsAdapter(view);
    }

    @Override
    public void onBindViewHolder(NewsAdapter holder, int position) {
        presenter.configureNewsRow(holder, position, context.getResources().getConfiguration().locale);
    }

    @Override
    public int getItemCount() {
        return presenter.getPostsCount();
    }

    public class NewsAdapter extends RecyclerView.ViewHolder implements FragmentNewsRowView{

        private TextView date;
        private ImageView picture;
        private TextView message;
        private  ImageView indicator;

        public NewsAdapter(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.row_fragment_news_item_date);
            picture = itemView.findViewById(R.id.row_fragment_news_item_image_view);
            message = itemView.findViewById(R.id.row_fragment_news_item_message);
            indicator = itemView.findViewById(R.id.row_fragment_news_item_indicator);
            indicator.setOnClickListener(this::onClick);
        }

        private void onClick(View view){
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ObjectAnimator animation;
            if(view.isSelected()){
                animation = ObjectAnimator.ofInt(
                        message, "maxLines", 6);
                animation.setDuration(300);
                animation.start();
                p.removeRule(RelativeLayout.BELOW);
                p.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.row_fragment_news_item_message);
                view.setSelected(false);

            }else{
                animation = ObjectAnimator.ofInt(
                        message, "maxLines", message.getLineCount());
                animation.setDuration(300);
                p.addRule(RelativeLayout.BELOW, R.id.row_fragment_news_item_message);
                p.removeRule(RelativeLayout.ALIGN_BOTTOM);
                view.setSelected(true);
            }
            animation.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    view.setLayoutParams(p);
                    view.requestLayout();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            animation.start();

        }

        @Override
        public void setDate(String date) {
            this.date.setText(date);
        }

        @Override
        public void setPicture(String url) {

        }

        @Override
        public void setMessage(String message) {
            this.message.setText(message);
            Linkify.TransformFilter filter = (match, url) ->
                    match.group().replace("#", "");
            Pattern hashtagPattern = Pattern.compile("#([A-Za-z0-9_-]+)");
            String hashtagScheme = "fb://facewebmodal/f?href=https://www.facebook.com/hashtag/";
            Linkify.addLinks(this.message, hashtagPattern, hashtagScheme, null, filter);
            Pattern emailPattern = Patterns.EMAIL_ADDRESS;
            Linkify.addLinks(this.message, emailPattern, null, null, filter);
            Pattern urlPattern = Patterns.WEB_URL;
            Linkify.addLinks(this.message, urlPattern, null,
                    Linkify.sUrlMatchFilter::acceptMatch,
                    null);
        }
    }
}
