package com.asiainfo;

/**
 * 单例模式——懒汉式
 *
 * @author zhangzhiwang
 * @date 2018年7月22日 下午3:48:57
 */ 
public class Singleton2 {
	private static volatile Singleton2 singleton2;//synchronized是万能的或者说是通用的，它可以完全替代volatile的功能，但反过来volatile不能替代symchronized。那为什么还保留volatile呢？因为volatile是轻量级锁，synchronized是重量级锁，在保证可见性方面volatile的效率要比synchronized高。还有volatile是保证可见性的不能保证原子性，所以不能取代synchronized。
	
	private Singleton2() {}
	
	public static Singleton2 getInstance() {//由于饿汉式的getInstance()方法不是原子性的所以要加上同步，但是考虑到性能问题，在方法上加synchronized关键字不太好，于是考虑减小锁的粒度只在重建实例的时候加锁，但是又考虑到指令重排序的问题最终抛弃使用synchronized关键字而改为使用volatile关键字
		if(singleton2 == null) {
			singleton2 = new Singleton2();
			return singleton2;
		}
		
		return singleton2;
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 20; i++) {
			new Thread() {
				@Override
				public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {  
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "-->" + Singleton2.getInstance());
				}
			}.start();
		}
	}
}
