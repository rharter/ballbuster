package com.ballbuster;

import android.graphics.*;

public abstract class Entity {

	public static final int NORMAL = 0;
	public static final int DESTROYED = 1;

	protected RectF mFrame;
	protected Paint mPaint;
	protected int mState;

	public Entity() {
		setPaint(new Paint());
		setColor(Color.WHITE);
	}

	public abstract void update(long time);

	public abstract void draw(Canvas canvas);

	public abstract void handleCollision(Entity e);

	public boolean checkCollision(Entity e) {
		return RectF.intersects(e.getFrame(), mFrame);
	}

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

	public void setState(int state) {
		mState = state;
	}

	public int getState() {
		return mState;
	}
}