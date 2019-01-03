package com.asiainfo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁——读写互斥、写写互斥、读读不互斥
 *
 * @author zhangzhiwang
 * @date 2018年7月25日 下午8:57:14
 */
public class ReadAndWriteLock {
	private Map<String, Object> map = new HashMap<>();
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();//读写锁
	private Lock writeLock = readWriteLock.writeLock();//从读写锁获得写锁
	private Lock readLock = readWriteLock.readLock();//从读写锁获取读锁

	public void put(String key, Object value) {
		writeLock.lock();
		String cur = Thread.currentThread().getName();
		System.out.println(cur + "正在写...");
		try {
			Thread.sleep(2000);
			map.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writeLock.unlock();
			System.out.println(cur + "写完毕!");
		}
	}

	public Object get(String key) {
		
//		String s="";
//		String s="";
//		String s="";
//		String s="";
		
		readLock.lock();
		String cur = Thread.currentThread().getName();
		System.out.println(cur + "正在读...");
		try {
			Thread.sleep(2000);
			return map.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			readLock.unlock();
			System.out.println(cur + "读完毕!");
		}
		return null;
	}
	
//	public static void main(String[] args) {
//		final ReadAndWriteLock test = new ReadAndWriteLock();
////		test.map.put("key", "value");
//		//测试写写互斥/读读不互斥
////		for(int i = 0; i < 3; i++) {
////			new Thread(new Runnable() {
////				@Override
////				public void run() {
//////					test.put("key", "value_");
////					test.get("key");
////				}
////			}).start();
////		}
//		
//		//测试读写互斥
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				test.put("key", "value_");
////				test.get("key");
//			}
//		}).start();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
////				test.put("key", "value_");
//				test.get("key");
//			}
//		}).start();
//	}
	
	public static void main(String[] args) {
		float f = 7.22f - 7.0f;
		double d = 7.22-7.0;
		System.out.println("7.22-7.0=");
		System.out.println("float:" + f);
		System.out.println("double:" + d);
		
		BigDecimal b1 = new BigDecimal(String.valueOf(7.22));
		BigDecimal b2 = new BigDecimal(String.valueOf(7.0));
		System.out.println("decimal:" + b1.subtract(b2).doubleValue());
	}
}

