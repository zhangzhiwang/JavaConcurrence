package com.asiainfo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * java.util.concurrent下的原子类
 *
 * @author zhangzhiwang
 * @date 2018年7月22日 下午6:20:09
 */
public class Atomic1 {
	private AtomicInteger value = new AtomicInteger(0);
	
	public int incr() {
		int num = 0;
//		num = value.incrementAndGet();//先自增再获取值，相当于++value
//		num = value.getAndIncrement();//先获取值再自增，相当于value++
		num = value.addAndGet(10);//增加指定的值后在获取值
		num = value.decrementAndGet();//--value
		return num;
	}
	
	public static void main(String[] args) {
		final Atomic1 threadSecurity = new Atomic1();
//		for (int i = 0; i < 3; i++) {
//			Thread thread = new Thread(new Runnable() {
//				@Override
//				public void run() {
//					for (int j = 0; j < 10000; j++) {
//						System.out.println(Thread.currentThread().getName() + "-->" + threadSecurity.incr());
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			});
//			thread.setName("thread_" + i);
//			thread.start();
//		}
		System.out.println(threadSecurity.incr());
	}
}
