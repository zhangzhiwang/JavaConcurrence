package com.asiainfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试自己实现的基于AQS的可重入锁
 *
 * @author zhangzhiwang
 * @date 2018年7月24日 下午10:31:51
 */
public class TestMyReentryLockWithAQS {
	private int value;
	private MyReentryLockWithAQS myReentryLockWithAQS = new MyReentryLockWithAQS();

	public int incr() {
		myReentryLockWithAQS.lock();
		value = value + 1;
		myReentryLockWithAQS.unlock();
		return value;
	}

	public static void main(String[] args) throws InterruptedException {
		final TestMyReentryLockWithAQS test = new TestMyReentryLockWithAQS();

		// 测试线程安全
		// for(int i = 0; i < 10; i++) {
		// Thread thread = new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// for(int i = 0; i < 100; i++) {
		// try {
		// Thread.sleep(1);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// System.out.println(Thread.currentThread().getName() + "-->" + test.incr());
		// }
		// }
		// });
		// thread.start();
		// }
		//
		// Thread.sleep(10000);
		// System.out.println("final value = " + test.value);

		// 测试可重入
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				test.a();
			}
		});
		thread.start();

	}

	public void a() {
		myReentryLockWithAQS.lock();
		System.out.println(Thread.currentThread().getName() + "--> a");
		b();
		myReentryLockWithAQS.unlock();
	}

	public void b() {
		myReentryLockWithAQS.lock();
		System.out.println(Thread.currentThread().getName() + "--> b");
		myReentryLockWithAQS.unlock();
	}
}
