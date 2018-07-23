package com.asiainfo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自己实现的不可重入锁
 *
 * @author zhangzhiwang
 * @date 2018年7月23日 上午8:08:56
 */
public class MyNoReentryLock implements Lock {
	private boolean isLocked;
	private static final MyNoReentryLock MY_NO_REENTRY_LOCK = new MyNoReentryLock();

	@Override
	public synchronized void lock() {//思考两个问题：1、既然自己实现了锁，那为什么还用synchronized关键字同步？因为如果不使用synchronized那么多个线程可能同时获得锁；2、既然lock()和unlock()方法都使用了synchronized关键字，那么还有必要实现自己的锁吗？有必要，因为即使加锁和释放锁的方法都加了synchronized关键字，如果两个方法里面不实现自己的锁那么会出现加锁和释放锁不是同一个线程，即A线程加了锁，B线程去释放锁。
		//锁有两个特点：1、排他性，即一个线程获取了锁其他线程就不能在获取该锁了，如果自己实现的lock()方法和unlock()方法都没有进行synchronized同步，那么可能会出现排他性问题；2、谁获取锁谁释放锁，如果lock()方法和unlock()方法只使用synchronized进行同步而不实现自己的锁逻辑，那么可能出现A获取了锁，B去释放锁。
		// 其实lock()方法和unlock()方法里面干的事只有一个，那就是保证调用这两个方法的线程是同一个才有用，如果不是同一个线程调用的就不起作用
		if(isLocked) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		isLocked = true;
		
	}
	
	@Override
	public synchronized void unlock() {
		isLocked = false;
		notifyAll();
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		
		new Thread() {
			@Override
			public void run() {
				MY_NO_REENTRY_LOCK.a();
			}
		}.start();
	}
	
	public void a() {
		MY_NO_REENTRY_LOCK.lock();
		System.out.println("a");
		b();
		MY_NO_REENTRY_LOCK.unlock();
	}
	
	public void b() {
		MY_NO_REENTRY_LOCK.lock();
		System.out.println("b");
		MY_NO_REENTRY_LOCK.unlock();
	}
	
}
