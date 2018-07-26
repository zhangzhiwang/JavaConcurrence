package com.asiainfo;

/**
 * 线程间通信wait和notify
 * 
 * @author zhangzhiwang
 * @date 2018年7月26日 下午1:02:00
 */
public class WaitAndNotify {
	private volatile int num;

	public synchronized int getNum() throws InterruptedException {
		String cur = Thread.currentThread().getName();
		System.out.println(cur + "开始读...");
		if(num != 1) {
			wait();//使处于运行状态的线程进入等待状态，并释放锁，要放在同步块里面调用
		}
		System.out.println(cur + "读完毕！");
		return num;
	}

	public synchronized void setNum() throws InterruptedException {
		String cur = Thread.currentThread().getName();
		System.out.println(cur + "开始写...");
		Thread.sleep(3000);//使处于运行状态的线程进入等待状态，不释放锁
		this.num = 1;
		System.out.println(cur + "写完毕！");
		notifyAll();//要放在同步块里面调用。notify方法随机叫醒一个由于wait而处于等待状态的线程，使之进入就需状态；nofityAll方法会唤醒所有由于wait而处于等待状态的线程，使它们进入就需状态
	}
	
	public static void main(String[] args) throws InterruptedException {
		final WaitAndNotify waitAndNotify = new WaitAndNotify();
		for(int i = 0; i < 3; i++) {
			new Thread() {
				@Override
				public void run() {
					try {
						waitAndNotify.getNum();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
		
		Thread.sleep(3000);
		new Thread() {
			@Override
			public void run() {
				try {
					waitAndNotify.setNum();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
}
