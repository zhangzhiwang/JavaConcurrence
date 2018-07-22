package com.asiainfo;

/**
 * 创建线程的方式2——实现Runnable接口
 *
 * @author zhangzhiwang
 * @date 2018年7月21日 下午6:13:43
 */
public class CreateThread2 implements Runnable {
	@Override
	public void run() {//Runnable接口里面就一个run()方法。无论是通过继承Thread还是实现Runnable接口，都要重写run()方法
		System.out.println(Thread.currentThread().getName() + "线程执行");
	}
	
	public static void main(String[] args) {
//		CreateThread2 createThread2 = new CreateThread2();//run()方法是Runnable接口定义的而start()方法是Thread定义的，所以以实现Runnable接口的方式来创建线程必须创建一个Thread类的实例来启动线程
//		Thread thread = new Thread(createThread2);
//		thread.setName("createThread2");
//		thread.start();
		
		//以匿名内部类的方式来创建线程
		//方式1：
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("run...");
//			}
//		}).start();
		
		//方式2:
//		new Thread() {
//			@Override
//			public void run() {
//				System.out.println("thread...");
//			}
//		}.start();
		
		//思考：将以上两种方式结合会打印什么结果？
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("run...");
//			}
//		}) {
//			@Override
//			public void run() {
//				System.out.println("thread...");//为什么会打印“thread...”？答案就是多太，匿名内部类就是Thread的子类
//			}
//		}.start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("run...");//这个为什么又打印“run...”了？因为Thread的子类（匿名内部类）没有重写run()方法会执行父类的run()方法，而父类的run()方法就是Runnable实现类（也是个匿名内部类）里面重写的run()方法
			}
		}) {
			public void met1() {
				System.out.println("thread...");//这里的父子关系为：儿子（Thread的匿名内部类）、父亲（Thread）、爷爷（Runnable）
			}
		}.start();
	}
}
