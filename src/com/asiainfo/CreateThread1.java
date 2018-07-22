package com.asiainfo;

/**
 * 创建线程的第一种方式——继承Thread类
 *
 * @author zhangzhiwang
 * @date 2018年7月21日 下午5:29:07
 */
public class CreateThread1 extends Thread {
	public CreateThread1() {}
	
	public CreateThread1(String name) {
		super(name);
	}
	
	@Override
	public void run() {//通过阅读源码得知：Thread类实现了Runnable接口，Runnable接口里面只有一个方法即run()方法，而Thread再重写run()方法的时候什么都没做，所以自定义的线程类必须重写run()方法
		while(!interrupted()) {//判断线程是否被中断有两个方法：interrupted()和isInterrupted()，两者是等效的，因为方法实现是一样的，只不过前者是实例方法后者是静态方法，看下源码就知道了
			System.out.println(getName() + "自定义线程运行");
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		CreateThread1 createThread1 = new CreateThread1("createThread1");
//		createThread1.setDaemon(true);//设置守护线程，程序不会等待守护线程的运行完毕才退出，当主线程执行完毕后不管守护线程是否执行完毕程序就会退出
		createThread1.start();
		
		Thread.sleep(1000);
		createThread1.interrupt();
	}
}
