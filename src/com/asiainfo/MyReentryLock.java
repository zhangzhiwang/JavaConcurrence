package com.asiainfo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


/**
 * 自己实现的可重入锁
 * 
 * @author zhangzhiwang
 * @date 2018年7月23日 下午12:54:39
 */
public class MyReentryLock implements Lock {
	private static final MyReentryLock MY_REENTRY_LOCK = new MyReentryLock();
	private Thread lockedBy;
	private int lockCount;
	private boolean isLocked;

	@Override
	public synchronized void lock() {
		Thread currentThread = Thread.currentThread();
		while(isLocked && currentThread != lockedBy) {//currentThread != lockedBy是控制可重入的关键
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isLocked = true;
		lockedBy = currentThread;
		lockCount++;
	}

	@Override
	public synchronized void unlock() {
		Thread currentThread = Thread.currentThread();
		if(currentThread != lockedBy) {
			return;
		}
		isLocked = false;
		lockCount--;
		if(lockCount == 0) {
			lockedBy = null;
			notifyAll();
		}
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
		for(int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					MY_REENTRY_LOCK.a();
				}
			});
			thread.start();
		}
	}
	
	public void a() {
		MY_REENTRY_LOCK.lock();
		System.out.println(Thread.currentThread().getName() + "--> a");
		b();
		MY_REENTRY_LOCK.unlock();
	}
	
	public void b() {
		MY_REENTRY_LOCK.lock();
		System.out.println(Thread.currentThread().getName() + "--> b");
		MY_REENTRY_LOCK.unlock();
	}
}
