package com.asiainfo;

import java.util.concurrent.SynchronousQueue;

/**
 * 单例模式——饿汉式
 *
 * @author zhangzhiwang
 * @date 2018年7月22日 下午3:44:12
 */
public class Singleton1 {
	private static Singleton1 singleton1 = new Singleton1();
	
	private Singleton1() {}
	
	public static Singleton1 getInstance() {//饿汉式的getInstance()方法是原子性的，所以没有线程安全问题，故不用加synchronized关键字同步
		return singleton1;
	}
	
	public static void main(String[] args) {
		while(true) {
			new Thread() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + "-->" + Singleton1.getInstance());
				}
			}.start();
		}
	}
}
