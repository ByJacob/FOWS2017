package pl.edu.pwr.fows.fows2017.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.util.Linkify;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
    private int lastPosition = -1;

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
        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return presenter.getPostsCount();
    }

    public class NewsAdapter extends RecyclerView.ViewHolder implements FragmentNewsRowView {

        private TextView date;
        private ImageView picture;
        private TextView message;
        private ImageView indicator;

        public NewsAdapter(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.row_fragment_news_item_date);
            picture = itemView.findViewById(R.id.row_fragment_news_item_image_view);
            message = itemView.findViewById(R.id.row_fragment_news_item_message);
            indicator = itemView.findViewById(R.id.row_fragment_news_item_indicator);
            indicator.setOnClickListener(this::onClick);
        }

        private void onClick(View view) {
            RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ObjectAnimator animation;
            if (view.isSelected()) {
                animation = ObjectAnimator.ofInt(
                        message, "maxLines", 6);
                animation.setDuration(300);
                animation.start();
                p.removeRule(RelativeLayout.BELOW);
                p.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.row_fragment_news_item_message);
                view.setSelected(false);

            } else {
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
        public void setPicture(String url, Integer position) {
            Picasso.with(context).load(url).into(picture);
            FragmentNewsRowView adapter = this;
            picture.setOnClickListener(view -> presenter.onPictureClick(position, adapter));
        }

        @Override
        public void setMessage(String message) {
            this.message.setText(message);
            Linkify.TransformFilter filterHashTag = (match, url) ->
                    match.group().replace("#", "");
            Pattern hashtagPattern = Pattern.compile("#([\\w]+)");
            String hashtagScheme = "fb://facewebmodal/f?href=https://www.facebook.com/hashtag/";
            Linkify.addLinks(this.message, hashtagPattern, hashtagScheme, null, filterHashTag);
            Pattern emailPattern = Patterns.EMAIL_ADDRESS;
            Linkify.addLinks(this.message, emailPattern, null, null, null);
            Pattern urlPattern = Patterns.WEB_URL;
            Linkify.TransformFilter filterURL = (matcher, s) -> {
                String url = matcher.group();
                if (!url.contains("http://"))
                    url = "http://" + url;
                return url;
            };
            Linkify.addLinks(this.message, urlPattern, null,
                    Linkify.sUrlMatchFilter, filterURL);
            this.message.setOnTouchListener(this::onMessageClick);
        }

        @Override
        public void openAppSendEmail(String email) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback - " + this.date.getText().toString());
            context.startActivity(Intent.createChooser(intent, "Send Email"));
        }

        @Override
        public void openFacebook(String url) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("fb://facewebmodal/f?href=" + url));
            context.startActivity(i);
        }

        private boolean onMessageClick(View view, MotionEvent motionEvent) {
            TextView tv = (TextView) view;
            if (motionEvent.getActionMasked() == MotionEvent.ACTION_UP) {
                int y = (int) motionEvent.getY();

                Layout layout = tv.getLayout();
                int line = layout.getLineForVertical(y);
                int start, end;
                if (line <= 0) {
                    start = layout.getLineStart(line);
                    end = layout.getLineEnd(line + 1);
                } else if (line >= tv.getLineCount() - 1) {
                    start = layout.getLineStart(line - 1);
                    end = layout.getLineEnd(line);
                } else {
                    start = layout.getLineStart(line - 1);
                    end = layout.getLineEnd(line + 1);
                }
                String clickLine = tv.getText().toString().substring(start, end);
                return presenter.onMessageClick(clickLine, this);
            }
            return false;
        }
    }
}
