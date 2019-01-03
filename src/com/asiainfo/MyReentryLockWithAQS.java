package com.asiainfo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 使用AQS来实现重入锁
 *
 * @author zhangzhiwang
 * @date 2018年7月24日 下午9:58:40
 */
public class MyReentryLockWithAQS implements Lock {
	private Helper helper = new Helper();
	
	private class Helper extends AbstractQueuedSynchronizer {
		@Override
		protected boolean tryAcquire(int arg) {
			Thread currentThread = Thread.currentThread();
			int state = getState();//每加一次锁state+1，如果state是0说明没有加过锁或者锁全部被释放掉了
			if(compareAndSetState(0, arg)) {// 第一个参数是期望值，即判断当前的值是否是期望值，如果是则设置当前值是第二个参数arg，如果不是则返回false
				//如果实际值和期望值相等且是0，则说明尚未上过锁，此时该线程可以获得锁并将state+1。compareAndSetState方法本身就是再多线程环境下保证线程安全问题
				//记录获得锁的线程为当前线程
				setExclusiveOwnerThread(currentThread);
				return true;
			} else if(currentThread == getExclusiveOwnerThread()) {
				setState(state + arg);
				return true;
			}
			return false;
		}
		
		@Override
		protected boolean tryRelease(int arg) {
			//锁的获取和释放必须是同一个线程的行为，即谁获得锁谁释放锁
			//判断获得锁的线程是否为当前线程
			if(getExclusiveOwnerThread() != Thread.currentThread()) {
				return false;
			}
			
			int state = getState() - arg;
			setState(state);
			if(state != 0) {
				return false;
			}
			
			setExclusiveOwnerThread(null);
	        return true;
	    }
	}

	@Override
	public void lock() {
		helper.acquire(1); 

	}

	@Override
	public void unlock() {
		helper.release(1);

	}

	@Override
	public boolean tryLock() {
		return helper.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return helper.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		helper.acquireInterruptibly(1);
	}

	@Override
	public Condition newCondition() {
		return null;
	}

}
