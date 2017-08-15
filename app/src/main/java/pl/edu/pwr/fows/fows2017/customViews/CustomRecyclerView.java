package pl.edu.pwr.fows.fows2017.customViews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Project: FoWS2017
 * Created by Jakub Rosa on 15.08.2017.
 */

public class CustomRecyclerView extends RecyclerView {

    private Integer maxVelocityY;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setMaxVelocityY(Integer maxVelocityY) {
        this.maxVelocityY = maxVelocityY;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        if (velocityY > maxVelocityY)
            velocityY = this.maxVelocityY;
        if (velocityY < -maxVelocityY)
            velocityY = -maxVelocityY;
        return super.fling(velocityX, velocityY);
    }
}
