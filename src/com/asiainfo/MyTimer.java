package com.asiainfo;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器（java.util.Timer很少使用，一般用Quartz来取代）
 *
 * @author zhangzhiwang
 * @date 2018年7月21日 下午7:25:46
 */
public class MyTimer {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("定时器启动");

			}
		}, 1, 1);
	}
}
