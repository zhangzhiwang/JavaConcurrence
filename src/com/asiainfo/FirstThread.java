package com.asiainfo;

/**
 * 第一个线程类
 *
 * @author zhangzhiwang
 * @date 2018年7月21日 下午4:48:21
 */
public class FirstThread implements Runnable {
	@Override
	public synchronized void run () {
		while(true) {
			System.out.println("自定义线程运行......");
			try {
				wait();//处于运行状态的线程遇到wait()方法时会处于等待状态，等待notify()或者notifyAll()方法的调用，然后又回到就绪状态
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		FirstThread firstThread = new FirstThread();
		Thread thread = new Thread(firstThread);//当new一个线程类的实例的时候线程处于初始状态
		thread.start();//当调用线程实例的start()方法时，该线程进入就绪状态，当该线程得到CPU时间时会进入运行状态
		
		while(true) {
			synchronized(firstThread) {
				System.out.println("主线程执行");
				Thread.sleep(10);//当运行状态的线程遇到sleep()方法时会进入超时等待状态，当达到规定时间后会自动重新进入就绪状态
				firstThread.notifyAll();//当由于遇到wait()方法而处于等待状态的线程遇到了notify()或者notifyAll()方法被唤醒，又重新进入就绪状态
				//还有一种线程状态是阻塞状态，当处于运行状态的线程等待IO或者等待获取锁的时候会进入阻塞状态，然后该线程获取了IO或者锁的时候会重新进入运行状态（这里和等待状态不一样，等待状态恢复后是进入就绪状态）
			}
		}
	}
}
