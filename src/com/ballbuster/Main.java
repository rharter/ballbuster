package com.ballbuster;

import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Game game = new Game(this);
        setContentView(game);

        game.initialize();
        game.run();
    }
}
