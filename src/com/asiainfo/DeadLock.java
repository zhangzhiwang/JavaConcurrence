package com.asiainfo;

/**
 * 线程死锁
 *
 * @author zhangzhiwang
 * @date 2018年7月22日 下午5:20:02
 */
public class DeadLock {
	private Object o1 = new Object();
	private Object o2 = new Object();
	
	public void a() {
		synchronized(o1) {//当一个线程持有A锁准备获取B锁，而另一个线程持有B锁准备获取A锁时，会发生线程死锁
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(o2) {
				System.out.println("a");
			}
		}
	}
	
	public void b() {
		synchronized(o2) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(o1) {
				System.out.println("b");
			}
		}
	}
	
	public static void main(String[] args) {
		final DeadLock deadLock = new DeadLock();
		
		new Thread() {
			@Override
			public void run() {
				deadLock.a();
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				deadLock.b();
			}
		}.start();
	}
}
