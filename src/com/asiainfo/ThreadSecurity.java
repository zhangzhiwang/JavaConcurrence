package com.asiainfo;

/**
 * 模拟线程安全
 *
 * @author zhangzhiwang
 * @date 2018年7月22日 下午3:10:33
 */
public class ThreadSecurity {
	private int value;

	public synchronized int incr() {
		value = value + 1;
		return value;
	}

	public static void main(String[] args) {
		final ThreadSecurity threadSecurity = new ThreadSecurity();
		// 单线程中测试
//		 while(true) {
//			 System.out.println(threadSecurity.incr());
//		 }

		// 多线程中测试
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 10000; j++) {
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
	}
}