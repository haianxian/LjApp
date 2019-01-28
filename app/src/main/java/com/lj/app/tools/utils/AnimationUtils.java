package com.lj.app.tools.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

/**
 * Created by 13717 on 2018/11/2.
 */

public class AnimationUtils {

    private static final AccelerateDecelerateInterpolator ACCELERATE_DECELERATE_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final OvershootInterpolator OVERSHOOT_INTERPOLATOR = new OvershootInterpolator(4);

    // 动态点亮动画
    public static void lightAnim(final DotsView dotsView,final TextView lightIv){
        final AnimatorSet set = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(lightIv, "scaleX", 1f,1.2f);
        scaleX.setDuration(200);

        ObjectAnimator scaleY = ObjectAnimator.ofFloat(lightIv, "scaleY", 1f,1.2f);
        scaleY.setDuration(200);

        // 开花
        final ObjectAnimator dotsAnimator = ObjectAnimator.ofFloat(dotsView, DotsView.DOTS_PROGRESS, 0, 1f);
        dotsAnimator.setDuration(900);
        dotsAnimator.setStartDelay(50);
        dotsAnimator.setInterpolator(ACCELERATE_DECELERATE_INTERPOLATOR);

        set.playTogether(scaleX,scaleY, dotsAnimator);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (lightIv == null) return;
                AnimatorSet set1 = new AnimatorSet();
                ObjectAnimator scaleX1 = ObjectAnimator.ofFloat(lightIv, "scaleX", 1.2f,1f);
                ObjectAnimator scaleY1 = ObjectAnimator.ofFloat(lightIv, "scaleY", 1.2f,1f);
                set1.playTogether(scaleX1,scaleY1);
                set1.setDuration(200).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                if(dotsView == null) return;
                dotsView.setCurrentProgress(0);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }
}
