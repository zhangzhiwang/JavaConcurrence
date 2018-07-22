package com.asiainfo;

/**
 * 对Synchronzied关键字的理解
 *
 * @author zhangzhiwang
 * @date 2018年7月22日 下午5:03:52
 */
public class Sync2 {
	private Object object = new Object();
	
	public synchronized void a() {//a方法和b方法锁的是同一个对象（this），当线程1执行a方法获取了锁，当执行完毕释放锁之前，线程2执行b方法，由于线程1获取了this的锁还未释放，所以线程2阻塞了
		System.out.println("a");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void b() {
		System.out.println("b");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static void c() {//静态方法和非静态方法同理，只不过静态方法锁住的是同一个对象——Sync2的Class对象
		System.out.println("c");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static void d() {
		System.out.println("d");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void e() {
		synchronized(object) {//synchronized关键字放在方法上和放在方法内修饰代码块的本质是一样的，即锁住的是同一个对象，要么是this，要么是Class对象，要么是其他同一对象
			System.out.println("e");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void f() {
		synchronized(object) {
			System.out.println("f");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final Sync2 sync2 = new Sync2();
		
		new Thread() {
			@Override
			public void run() {
				sync2.a();
			}
		}.start();
		
		new Thread() {
			@Override
			public void run() {
				sync2.e();
			}
		}.start();
	}
}
