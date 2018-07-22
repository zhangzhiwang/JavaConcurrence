package com.asiainfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建线程方式4——用线程池创建	
 *
 * @author zhangzhiwang
 * @date 2018年7月21日 下午7:29:15
 */
public class CreateThread4 {
	public static void main(String[] args) {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 100; i++) {
			newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}
		newFixedThreadPool.shutdown();
	}
}
