package com.ballbuster;

import android.graphics.*;

public class Brick extends Entity {

	Paint mOutline;
	RectF mBody;
	int mLife = 1;

	public Brick(PointF origin, float width, float height) {
		super();
		setFrame(new RectF(origin.x, origin.y, origin.x + width, origin.y + height));
	}

	public void update(long time) {
	}


	public void draw(Canvas canvas) {
		canvas.drawRect(mFrame, mOutline);
		canvas.drawRect(mBody, mPaint);
	}

	public void handleCollision(Entity e) {
		if (e instanceof Ball) {
			((Ball) e).handleCollision(this);
		}
	}

	@Override
	public void setFrame(RectF rect) {
		super.setFrame(rect);

		mBody = new RectF(mFrame.left + 1.0f, mFrame.top + 1.0f,
				mFrame.right - 1.0f, mFrame.bottom - 1.0f);
	}

	@Override
	public void setColor(int color) {
		super.setColor(color);

		mOutline = new Paint(mPaint);
		mOutline.setAlpha(128);
	}

	@Override
	public boolean checkCollision(Entity e) {
		if (e instanceof Ball) {
			return RectF.intersects(e.getFrame(), mFrame);
		}
		return false;
	}

	public void setLife(int life) {
		mLife = life;
	}

	public void handleDamage(int damage) {
		mLife -= damage;
		Log.v("Handling damage: " + damage);
		if (mLife <= 0) {
			mState = DESTROYED;
		}
	}
}