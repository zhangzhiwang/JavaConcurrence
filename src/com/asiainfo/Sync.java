package com.asiainfo;

/**
 * synchronized关键字
 *
 * @author zhangzhiwang
 * @date 2018年7月22日 下午3:38:36
 */
public class Sync {
	private int value;
	
	public synchronized void incr() {//synchronized放在普通方法上锁住的是当前对象this
	}
	
	public synchronized static void met1() {//synchronized放在静态方法上锁住的是类的Class信息
		
	}
	
	public void met2() {
		System.out.println("do something1");
		
		synchronized(this) {//synchronized修饰代码块可以锁住任意对象
			System.out.println("do something2");
		}
		
		System.out.println("do something3");
	}
}
