package com.christain.appbase.widget.flycodialog.animation.ZoomEnter;

import android.view.View;

import com.christain.appbase.widget.flycodialog.animation.BaseAnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class ZoomInEnter extends BaseAnimatorSet {
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1),//
				ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1),//
				ObjectAnimator.ofFloat(view, "alpha", 0, 1));//
	}
}
