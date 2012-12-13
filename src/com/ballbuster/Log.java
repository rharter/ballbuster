package com.ballbuster;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Package level logging
 */
public class Log {
	public final static String LOGTAG = "BallBuster";

	public static final boolean LOGV = true;

	public static void v(String logMe) {
		android.util.Log.v(LOGTAG, logMe);
	}

	public static void i(String logMe) {
		android.util.Log.i(LOGTAG, logMe);
	}

	public static void e(String logMe) {
		android.util.Log.e(LOGTAG, logMe);
	}

	public static void e(String logMe, Exception ex) {
		android.util.Log.e(LOGTAG, logMe, ex);
	}

	public static void w(String logMe) {
		android.util.Log.w(LOGTAG, logMe);
	}

	public static void wtf(String logMe) {
		android.util.Log.wtf(LOGTAG, logMe);
	}

	public static String formatTime(long millis) {
		return new SimpleDateFormat("HH:mm:ss.SSS aaa").format(new Date(millis));
	}
}