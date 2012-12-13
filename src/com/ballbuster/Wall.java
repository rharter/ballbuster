package com.ballbuster;

import android.graphics.*;

public class Wall extends Brick {

	public Wall(PointF origin, float width, float height) {
		super(origin, width, height);
	}

	public void update(long time) {
	}

	public void draw(Canvas canvas) {
	}
	public void handleDamage(int damage) {
		// infinite
	}
}