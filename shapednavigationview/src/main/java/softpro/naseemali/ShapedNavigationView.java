package softpro.naseemali;

/**
 * Created by Naseem on 18-10-2017.
 */


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.design.internal.NavigationMenuView;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by rom4ek on 10.01.2017.
 */

public class ShapedNavigationView extends NavigationView {

    private ShapedViewSettings settings;
    private int height = 0;
    private int width = 0;

    public ShapedNavigationView(Context context) {
        super(context);
        init(context, null);
    }

    public ShapedNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        settings = new ShapedViewSettings(context, attrs);

        /**
         * If hardware acceleration is on (default from API 14), clipPath worked correctly
         * from API 18.
         *
         * So we will disable hardware Acceleration if API < 18
         *
         * https://developer.android.com/guide/topics/graphics/hardware-accel.html#unsupported
         * Section #Unsupported Drawing Operations
         */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        setBackgroundColor(Color.TRANSPARENT);
        setInsetsColor(Color.TRANSPARENT);
    }

    private void setInsetsColor(int color) {
        try {
            Field insetForegroundField = ScrimInsetsFrameLayout.class.getDeclaredField("mInsetForeground");
            insetForegroundField.setAccessible(true);
            ColorDrawable colorDrawable = new ColorDrawable(color);
            insetForegroundField.set(this, colorDrawable);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View v = getChildAt(i);

            if (v instanceof NavigationMenuView) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.setBackground(settings.getBackgroundDrawable());
                } else {
                    v.setBackgroundDrawable(settings.getBackgroundDrawable());
                }
            }
        }
    }


    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        if (child instanceof NavigationMenuView) {
            child.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(),
                    View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(
                    getMeasuredHeight(), View.MeasureSpec.EXACTLY));
        } else {
            super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.clipPath(createClipPath(settings.getShapeType()));
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @SuppressLint("RtlHardcoded")
    private Path createClipPath(int type) {
        Path path=new Path();
        width=getMeasuredWidth();
        height=getMeasuredHeight();
        boolean rtl=getResources().getBoolean(R.bool.is_right_to_left);
        if(rtl==true) {
            switch (type) {
                case ShapedViewSettings.ARC_CONVEX:
                    path.moveTo(width, 0);
                    path.lineTo(width/8, 0);
                    path.quadTo(0, height / 2,
                            width/8, height);
                    path.lineTo(width, height);
                    path.close();
                    break;
                case ShapedViewSettings.ARC_CONCAVE:
                    path.moveTo(width, 0);
                    path.lineTo(0, 0);
                    path.quadTo(width / 8, height / 2,
                            0, height);
                    path.lineTo(width, height);
                    path.close();
                    break;
                case ShapedViewSettings.WAVES:
                    path.moveTo(width, 0);
                    path.quadTo(width / 2, 0, width / 2, height / 8);
                    path.quadTo(width / 2, height / 2 - width / 2, width / 2 - width / 4, height / 2 - width / 4);
                    path.quadTo(0, height / 2, width / 2 - width / 4, height / 2 + width / 4);
                    path.quadTo(width / 2, height / 2 + width / 2, width / 2, height - height / 8);
                    path.quadTo(width / 2, height, width, height);
                    path.close();
                    break;
                case ShapedViewSettings.WAVES_INDEFINITE:
                    int num = 51;
                    float wavewidth = height / num;
                    float mh = 0;
                    float fr = wavewidth / 2;
                    path.moveTo(width, 0);
                    path.lineTo(wavewidth, 0);
                    for (int i = 0; i < num + 1; ++i) {
                        if (i % 2 == 0) {
                            path.quadTo(0, mh += fr, wavewidth, mh += fr);
                        } else {
                            path.quadTo(2 * wavewidth, mh += fr, wavewidth, mh += fr);
                        }
                    }
                    path.lineTo(width, height);
                    path.close();
                    break;
                case ShapedViewSettings.ROUNDED_RECT:
                    path.moveTo(0, width / 8);
                    path.quadTo(0, 0, width / 8, 0);
                    path.lineTo(width - width / 8, 0);
                    path.quadTo(width, 0, width, width / 8);
                    path.lineTo(width, height - width / 8);
                    path.quadTo(width, height, width - width / 8, height);
                    path.lineTo(width / 8, height);
                    path.quadTo(0, height, 0, height - width / 8);
                    path.close();
                    break;
                case ShapedViewSettings.BOTTOM_ROUND:
                    path.moveTo(0, 0);
                    path.lineTo(width, 0);
                    path.lineTo(width, height - height / 4);
                    path.quadTo(width / 2, height, 0, height - height / 4);
                    path.close();
                    break;
                case ShapedViewSettings.FULL_ROUND:
                    path.moveTo(width, 0);
                    path.quadTo(0, 0, 0, height / 2);
                    path.quadTo(0, height, width, height);
                    path.close();
                    break;
                case ShapedViewSettings.NORMAL:
                    path.moveTo(0, 0);
                    path.lineTo(width, 0);
                    path.lineTo(width, height);
                    path.lineTo(0, height);
                    path.close();
            }
        }
        else{
            switch (type) {
                case ShapedViewSettings.ARC_CONVEX:
                    path.moveTo(0, 0);
                    path.lineTo(width, 0);
                    path.quadTo(width - width / 4, height / 2,
                            width, height);
                    path.lineTo(0, height);
                    path.close();
                    break;
                case ShapedViewSettings.ARC_CONCAVE:
                    path.moveTo(0, 0);
                    path.lineTo(width - width / 8, 0);
                    path.quadTo(width + width / 8, height / 2,
                            width - width / 8, height);
                    path.lineTo(0, height);
                    path.close();
                    break;
                case ShapedViewSettings.WAVES:
                    path.moveTo(0, 0);
                    path.quadTo(width / 2, 0, width / 2, height / 8);
                    path.quadTo(width / 2, height / 2 - width / 2, width / 2 + width / 4, height / 2 - width / 4);
                    path.quadTo(width, height / 2, width / 2 + width / 4, height / 2 + width / 4);
                    path.quadTo(width / 2, height / 2 + width / 2, width / 2, height - height / 8);
                    path.quadTo(width / 2, height, 0, height);
                    path.close();
                    break;
                case ShapedViewSettings.WAVES_INDEFINITE:
                    path.moveTo(0, 0);
                    int num = 51;
                    float wavewidth = height / num;
                    path.lineTo(width - height / num, 0);
                    float mh = 0;
                    float fr = wavewidth / 2;
                    for (int i = 0; i < num + 1; ++i) {
                        if (i % 2 == 0) {
                            path.quadTo(width, mh += fr, width - wavewidth, mh += fr);
                        } else {
                            path.quadTo(width - 2 * wavewidth, mh += fr, width - wavewidth, mh += fr);
                        }
                    }
                    path.lineTo(0, height);
                    path.close();
                    break;
                case ShapedViewSettings.ROUNDED_RECT:
                    path.moveTo(0, width / 8);
                    path.quadTo(0, 0, width / 8, 0);
                    path.lineTo(width - width / 8, 0);
                    path.quadTo(width, 0, width, width / 8);
                    path.lineTo(width, height - width / 8);
                    path.quadTo(width, height, width - width / 8, height);
                    path.lineTo(width / 8, height);
                    path.quadTo(0, height, 0, height - width / 8);
                    path.close();
                    break;
                case ShapedViewSettings.BOTTOM_ROUND:
                    path.moveTo(0, 0);
                    path.lineTo(width, 0);
                    path.lineTo(width, height - height / 4);
                    path.quadTo(width / 2, height, 0, height - height / 4);
                    path.close();
                    break;
                case ShapedViewSettings.FULL_ROUND:
                    path.moveTo(0, 0);
                    path.quadTo(width, 0, width, height / 2);
                    path.quadTo(width, height, 0, height);
                    path.close();
                    break;
                case ShapedViewSettings.NORMAL:
                    path.moveTo(0, 0);
                    path.lineTo(width, 0);
                    path.lineTo(width, height);
                    path.lineTo(0, height);
                    path.close();
            }
        }
        return path;
    }

    public ShapedViewSettings getSettings(){
        return settings;
    }

}

