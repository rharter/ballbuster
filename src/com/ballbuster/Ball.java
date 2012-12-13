package com.ballbuster;

import android.graphics.*;

public class Ball extends Entity {

	PointF mVelocity;

	public Ball(PointF origin, float radius) {
		super();
		setFrame(new RectF(origin.x - radius, origin.y - radius, origin.x + radius, origin.y + radius));
	}

	public void update(long time) {
		float x = mVelocity.x * (time / 1000f);
		float y = mVelocity.y * (time / 1000f);
		mFrame.left += x;
		mFrame.right += x;
		mFrame.top += y;
		mFrame.bottom += y;
	}

	public void draw(Canvas canvas) {
		float radius = (mFrame.right - mFrame.left) / 2f;
		canvas.drawCircle(mFrame.left + radius, mFrame.top + radius, radius, mPaint);
	}

	public void setVelocity(PointF velocity) {
		mVelocity = velocity;
	}

	public void handleCollision(Entity e) {
		if (e instanceof Brick) {
			Brick b = (Brick) e;
			b.handleDamage(1);
		}

		float radius = (mFrame.right - mFrame.left) / 2f;
		if (e.getFrame().contains(mFrame.left, mFrame.top + radius) 
				|| e.getFrame().contains(mFrame.right, mFrame.top + radius)) {
			mVelocity.x = -mVelocity.x;
		} else {
			mVelocity.y = -mVelocity.y;
		}
	}
}