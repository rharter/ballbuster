package com.ballbuster;

import android.graphics.*;

public abstract class Entity {

	protected RectF mFrame;
	protected Paint mPaint;

	public Entity() {
		setPaint(new Paint());
		setColor(Color.WHITE);
	}

	public abstract void update(long time);

	public abstract void draw(Canvas canvas);

	public void setColor(int color) {
		mPaint.setColor(color);
	}

	public void setFrame(RectF rect) {
		mFrame = rect;
	}

	public RectF getFrame() {
		return mFrame;
	}

	public void setPaint(Paint paint) {
		mPaint = paint;
	}

	public Paint getPaint() {
		return mPaint;
	}
}