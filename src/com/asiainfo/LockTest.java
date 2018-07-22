package com.asiainfo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发包下的Lock类
 *
 * @author zhangzhiwang
 * @date 2018年7月22日 下午7:47:36
 */
public class LockTest {
	private int value;
	private Lock lock = new ReentrantLock();//小结：解决线程安全的方式：1、synchronized/volatile 2、并发包下的原子类 3、并发包下的锁（Lock）

	public int incr() {
		lock.lock();
		value = value + 1;
		lock.unlock();
		return value;
	}

	public static void main(String[] args) throws InterruptedException {
		final LockTest threadSecurity = new LockTest();
		// 多线程中测试
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 100; j++) {
						System.out.println(Thread.currentThread().getName() + "-->" + threadSecurity.incr());
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread.setName("thread_" + i);
			thread.start();
			
		}
		Thread.sleep(20000);
		System.out.println("最终value = " + threadSecurity.value);
	}
}
