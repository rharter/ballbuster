package com.ballbuster;

import java.util.ArrayList;
import java.util.List;

import android.graphics.*;
import android.content.Context;
import android.view.*;

public class Game extends View {

	private static final int[] ROW_COLOR = {
		Color.RED,
		Color.rgb(255, 128, 0),
		Color.YELLOW,
		Color.GREEN,
		Color.BLUE,
		Color.rgb(75, 0, 130),
		Color.rgb(143, 0, 255)
	};

	private Context mContext;

	private boolean mRunning;
	private long mLastUpdateTime;

	private List<Entity> mEntities;

	public Game(Context context) {
		super(context);
		mContext = context;
		mEntities = new ArrayList<Entity>();

		setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 
			ViewGroup.LayoutParams.MATCH_PARENT));
	}

	public void initialize() {
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);

		// Walls
		{
			float width = 10f;
			PointF left = new PointF(-width, 0f);
			Wall leftWall = new Wall(left, width, size.y);
			mEntities.add(leftWall);

			PointF top = new PointF(0f, -width);
			Wall topWall = new Wall(top, size.x, width);
			mEntities.add(topWall);

			PointF right = new PointF(size.x + width, 0f);
			Wall rightWall = new Wall(right, width, size.y);
			mEntities.add(rightWall);

			PointF bottom = new PointF(0f, size.y + width);
			Wall bottomWall = new Wall(bottom, size.x, width);
			mEntities.add(bottomWall);
		}

		// create the blocks
		int columns = 15;
		float width = size.x;
		float height = size.y;

		float brickWidth = width / columns;
		float brickHeight = brickWidth * 0.4f;

		float paddingTop = height / 10;

		for (int i = 0; i < columns; i++) {
			for (int j = 0; j < 7; j++) {

				PointF origin = new PointF((float)(brickWidth * i), (float)(brickHeight * j + paddingTop));
				Brick brick = new Brick(origin, (float) brickWidth, (float) brickHeight);
				brick.setColor(ROW_COLOR[j]);

				mEntities.add(brick);
			}
		}

		// create the paddle
		float paddleWidth = size.x;
		float paddleHeight = brickHeight;

		PointF origin = new PointF(0.0f, size.y - paddleHeight * 2);
		Brick paddle = new Brick(origin, (float) paddleWidth, (float) paddleHeight);
		paddle.setColor(Color.LTGRAY);
		paddle.setLife(Integer.MAX_VALUE);
		mEntities.add(paddle);

		float ballRadius = paddleHeight / 3f;
		PointF ballCenter = new PointF(origin.x + paddleWidth / 2f + ballRadius, origin.y - ballRadius);
		Ball ball = new Ball(ballCenter, ballRadius);
		ball.setColor(Color.WHITE);
		ball.setVelocity(new PointF(250f, -250f));
		mEntities.add(ball);
	}

	public void update(long time) {
		for (int i = 0; i < mEntities.size() - 1; i++) {
			for (int j = i + 1; j < mEntities.size(); j++) {
				Entity e1 = mEntities.get(i);
				Entity e2 = mEntities.get(j);
				if (e1.checkCollision(e2)) {
					e1.handleCollision(e2);
				}
			}
		}

		for (int i = 0; i < mEntities.size(); i++) {
			Entity e = mEntities.get(i);
			e.update(time);

			if (e.getState() == Entity.DESTROYED) {
				mEntities.remove(i);
				i--;
			}
		}
	}

	public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    	for (Entity e : mEntities) {
			e.draw(canvas);
		}
	}

	public void run() {
		new Thread(new Runnable() {

			public void run() {
				mRunning = true;
					
				mLastUpdateTime = System.currentTimeMillis();

				while (mRunning) {
					long frameTime = System.currentTimeMillis();

					update(System.currentTimeMillis() - mLastUpdateTime);
					postInvalidate();
					
					frameTime = System.currentTimeMillis() - frameTime;
					mLastUpdateTime = System.currentTimeMillis();
					if (frameTime < 16) {
						try {
							Thread.sleep(16 - frameTime);
						} catch (InterruptedException e) {
							Log.e("Didn't sleep", e);
						}
					}
				}
			}
		}).start();
	}
}