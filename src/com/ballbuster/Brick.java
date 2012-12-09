package com.ballbuster;

import android.graphics.*;

public class Brick extends Entity {

	Paint mOutline;
	RectF mBody;

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
}