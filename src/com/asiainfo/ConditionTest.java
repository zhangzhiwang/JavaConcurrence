package com.asiainfo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Condition来实现线程间交替执行的效果
 * 
 * 
 * @author zhangzhiwang
 * @date 2018年7月27日 下午9:35:23
 */
public class ConditionTest {
	private int num = 1;
	private Lock lock = new ReentrantLock();
	private Condition a = lock.newCondition();
	private Condition b = lock.newCondition();
	private Condition c = lock.newCondition();
	
	public void a() {
		lock.lock();
		System.out.println("a");
		while(num != 1) {
			try {
				a.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		num = 2;
		b.signal();
		lock.unlock();
	}

	public void b() {
		lock.lock();
		System.out.println("b");
		while(num != 2) {
			try {
				b.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		num = 3;
		c.signal();
		lock.unlock();
	}

	public void c() {
		lock.lock();
		System.out.println("c");
		while(num != 3) {
			try {
				c.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		num = 1;
		a.signal();
		lock.unlock();
	}
	
	public static void main(String[] args) {
		ConditionTest ct = new ConditionTest();
		
		new Thread(new T1(ct)).start();
		new Thread(new T2(ct)).start();
		new Thread(new T3(ct)).start();
	}
}

class T1 implements Runnable {
	private ConditionTest ct;

	public T1() {
		super();
	}

	public T1(ConditionTest ct) {
		super();
		this.ct = ct;
	}

	@Override
	public void run() {
		while(true) {
			ct.a();
		}
	}
}

class T2 implements Runnable {
	private ConditionTest ct;

	public T2() {
		super();
	}

	public T2(ConditionTest ct) {
		super();
		this.ct = ct;
	}

	@Override
	public void run() {
		while(true) {
			ct.b();
		}
	}
}

class T3 implements Runnable {
	private ConditionTest ct;

	public T3() {
		super();
	}

	public T3(ConditionTest ct) {
		super();
		this.ct = ct;
	}

	@Override
	public void run() {
		while(true) {
			ct.c();
		}
	}
}
