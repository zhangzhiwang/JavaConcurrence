package com.asiainfo;

/**
 * 锁重入
 *
 * @author zhangzhiwang
 * @date 2018年7月22日 下午4:56:56
 */
public class Lock1 {
	private Object object = new Object();
	
	public synchronized void a() {
		System.out.println("a");
		b();//锁重入：两个方法锁的是同一个对象，当一个线程获得a()方法的锁后，a方法调用b方法，b方法无需等待a方法释放锁后该线程才能访问b方法，可以直接方法b方法
	}
	
	public synchronized void b() {
		System.out.println("b");
	}
	
	public void c() {
		synchronized(object) {
			System.out.println("c");
			d();
		}
	}
	public void d() {
		synchronized(object) {
			System.out.println("d");
		}
	}
	
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				Lock1 lock1 = new Lock1();
				lock1.a();
				lock1.c();
			}
		} .start();
	}
}
