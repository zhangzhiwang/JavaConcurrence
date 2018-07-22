package com.asiainfo;

/**
 * 模拟自旋锁的原理
 *
 * @author zhangzhiwang
 * @date 2018年7月22日 下午5:16:46
 */
public class Spin {
	public static void main(String[] args) {
		new Thread() {
			@Override
			public void run() {
				System.out.println("a");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				System.out.println("b");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
		while(Thread.activeCount() != 1) {}//这就是自旋锁的原理，自旋锁相当于while(true) {}代码块为空，也就是空旋
		
		System.out.println("所有线程运行完毕");
	}
}
