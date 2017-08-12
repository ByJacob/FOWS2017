package pl.edu.pwr.fows.fows2017.tools;

import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;

/**
 * Project: FoWS2017
 * Created by damphat on 17.01.2017.
 */

@SuppressWarnings("CanBeFinal")
public final class Utils {
    /**
     * find child View in a ViewGroup by its position (x, y)
     *
     * @param parent the viewgourp
     * @param x      the x position in parent
     * @param y      the y position in parent
     * @return null if not found
     */
    public static View findChildByPosition(ViewGroup parent, float x, float y) {
        int count = parent.getChildCount();
        for (int i = count - 1; i >= 0; i--) {
            View child = parent.getChildAt(i);
            if (child.getVisibility() == View.VISIBLE) {
                if (isPositionInChildView(parent, child, x, y)) {
                    return child;
                }
            }
        }

        return null;
    }

    private static boolean isPositionInChildView(ViewGroup parent, View child, float x, float y) {
        sPoint[0] = x + parent.getScrollX() - child.getLeft();
        sPoint[1] = y + parent.getScrollY() - child.getTop();

        Matrix childMatrix = child.getMatrix();
        if (!childMatrix.isIdentity()) {
            childMatrix.invert(sInvMatrix);
            sInvMatrix.mapPoints(sPoint);
        }

        x = sPoint[0];
        y = sPoint[1];

        return x >= 0 && y >= 0 && x < child.getWidth() && y < child.getHeight();
    }

    private static Matrix sInvMatrix = new Matrix();
    private static float[] sPoint = new float[2];
}