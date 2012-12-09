package com.ballbuster;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.*;
import android.util.*;
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
		
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		Log.d("Game", "Density: " + metrics.density);

		Point size = new Point();
		display.getSize(size);

		Log.d("Game", "Size: " + size);

		int columns = 10;
		float width = size.x * metrics.density;
		float height = size.y * metrics.density;

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
	}

	public void update(long time) {
		for (Entity e : mEntities) {
			e.update(time);
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

				while (mRunning) {
					long frameTime = System.currentTimeMillis();

					update(System.currentTimeMillis() - mLastUpdateTime);
					postInvalidate();
					
					frameTime = System.currentTimeMillis() - frameTime;
					
					if (frameTime < 33) {
						try {
							Thread.sleep(33 - frameTime);
						} catch (InterruptedException e) {

						}
					}
					
					mLastUpdateTime = System.currentTimeMillis();
				}
			}
		}).start();
	}
}