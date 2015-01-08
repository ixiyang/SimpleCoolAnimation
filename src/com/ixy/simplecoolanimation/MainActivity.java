package com.ixy.simplecoolanimation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	private FrameLayout mFlOperation;
	private RelativeLayout mLlOperationContent;
	private LinearLayout mLlOperationItemWeibo;
	private LinearLayout mLlOperationItemGroupChat;
	private LinearLayout mLlOperationItemTask;
	private LinearLayout mLlOperationItemQingjia;
	private LinearLayout mLlOperationItemChuchai;
	private LinearLayout mLlOperationItemBaoxiao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mFlOperation = (FrameLayout) findViewById(R.id.fl_operation);
		mLlOperationContent = (RelativeLayout) LayoutInflater.from(this).inflate(
				R.layout.view_operation, mFlOperation, false);

		mLlOperationItemWeibo = (LinearLayout) mLlOperationContent
				.findViewById(R.id.operation_weibo);
		mLlOperationItemGroupChat = (LinearLayout) mLlOperationContent
				.findViewById(R.id.operation_group_chat);
		mLlOperationItemTask = (LinearLayout) mLlOperationContent.findViewById(R.id.operation_task);
		mLlOperationItemQingjia = (LinearLayout) mLlOperationContent
				.findViewById(R.id.operation_qingjia);
		mLlOperationItemBaoxiao = (LinearLayout) mLlOperationContent
				.findViewById(R.id.operation_baoxiao);
		mLlOperationItemChuchai = (LinearLayout) mLlOperationContent
				.findViewById(R.id.operation_chuchai);

		mLlOperationItemWeibo.setOnTouchListener(new OnTouch());
		mLlOperationItemBaoxiao.setOnTouchListener(new OnTouch());
		mLlOperationItemChuchai.setOnTouchListener(new OnTouch());
		mLlOperationItemGroupChat.setOnTouchListener(new OnTouch());
		mLlOperationItemQingjia.setOnTouchListener(new OnTouch());
		mLlOperationItemTask.setOnTouchListener(new OnTouch());
		mFlOperation.addView(mLlOperationContent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_new:
			showOperationView();
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private class OnTouch implements OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:

				PropertyValuesHolder pHolder = PropertyValuesHolder.ofFloat("scaleX", 1.2f);
				PropertyValuesHolder pHolder2 = PropertyValuesHolder.ofFloat("scaleY", 1.2f);
				ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(v, pHolder,
						pHolder2);
				animator.setDuration(500);
				animator.setInterpolator(new OvershootInterpolator(3.0f));
				animator.start();
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:

				pHolder = PropertyValuesHolder.ofFloat("scaleX", 1f);
				pHolder2 = PropertyValuesHolder.ofFloat("scaleY", 1f);
				animator = ObjectAnimator.ofPropertyValuesHolder(v, pHolder, pHolder2);
				animator.setDuration(500);
				animator.setInterpolator(new OvershootInterpolator(3.0f));
				animator.start();
				break;
			default:
				break;
			}
			return true;
		}

	}

	private ObjectAnimator animationIn(final View view) {

		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "y", view.getTop() + 500,
				view.getTop());
		animator.setDuration(400);
		animator.setInterpolator(new OvershootInterpolator(4.0f));
		animator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator arg0) {
				view.setVisibility(View.VISIBLE);

			}

			@Override
			public void onAnimationRepeat(Animator arg0) {

			}

			@Override
			public void onAnimationEnd(Animator arg0) {

			}

			@Override
			public void onAnimationCancel(Animator arg0) {

			}
		});
		return animator;
	}

	private ObjectAnimator animationOut(final View view) {
		ObjectAnimator animator = ObjectAnimator.ofFloat(view, "y", -200);
		animator.setDuration(600);
		animator.setInterpolator(new OvershootInterpolator());
		animator.addListener(new Animator.AnimatorListener() {

			@Override
			public void onAnimationStart(Animator arg0) {

			}

			@Override
			public void onAnimationRepeat(Animator arg0) {

			}

			@Override
			public void onAnimationEnd(Animator arg0) {
				view.setVisibility(View.INVISIBLE);

			}

			@Override
			public void onAnimationCancel(Animator arg0) {

			}
		});
		return animator;
	}

	private void animationIn() {
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.play(animationIn(mLlOperationItemGroupChat));
		animatorSet.start();

		animatorSet = new AnimatorSet();
		animatorSet.play(animationIn(mLlOperationItemWeibo))
				.with(animationIn(mLlOperationItemTask)).after(100);
		animatorSet.start();

		animatorSet = new AnimatorSet();
		animatorSet.play(animationIn(mLlOperationItemChuchai)).after(150);
		animatorSet.start();
		animatorSet = new AnimatorSet();
		animatorSet.play(animationIn(mLlOperationItemQingjia))
				.with(animationIn(mLlOperationItemBaoxiao)).after(250);
		animatorSet.start();
	}

	private void animationOut() {
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.play(animationOut(mLlOperationItemGroupChat));
		animatorSet.start();

		animatorSet = new AnimatorSet();
		animatorSet.play(animationOut(mLlOperationItemWeibo))
				.with(animationOut(mLlOperationItemTask)).after(100);
		animatorSet.start();

		animatorSet.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator arg0) {

			}

			@Override
			public void onAnimationRepeat(Animator arg0) {

			}

			@Override
			public void onAnimationEnd(Animator arg0) {

				mFlOperation.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onAnimationCancel(Animator arg0) {

			}
		});

		animatorSet = new AnimatorSet();
		animatorSet.play(animationOut(mLlOperationItemChuchai)).after(150);
		animatorSet.start();
		animatorSet = new AnimatorSet();
		animatorSet.play(animationOut(mLlOperationItemQingjia))
				.with(animationOut(mLlOperationItemBaoxiao)).after(250);
		animatorSet.start();
	}

	private void showOperationView() {
		mFlOperation.setVisibility(View.VISIBLE);
		animationIn();
	}

	private void hideOperationView() {
		animationOut();
	}

	@Override
	public void onBackPressed() {
		if (mFlOperation.getVisibility() == View.VISIBLE) {
			hideOperationView();
		} else {
			super.onBackPressed();
		}
	}
}
