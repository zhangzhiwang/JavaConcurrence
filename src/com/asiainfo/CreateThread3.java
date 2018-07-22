package com.asiainfo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的方式3——带返回值并可抛异常的方式
 *
 * @author zhangzhiwang
 * @date 2018年7月21日 下午6:55:08
 */
public class CreateThread3 implements Callable<Integer> {
	@Override
	public Integer call() throws InterruptedException {// Callable类里面只有一个call()方法，方法返回值类型取决于范型
		System.out.println("do something...");
		Thread.sleep(1000);
		return 1;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask = new FutureTask<>(new CreateThread3());
		Thread thread = new Thread(futureTask);
		thread.start();
		
		Thread.sleep(1000);
		Integer i = futureTask.get();
		System.out.println("i = " + i);
	}
}
