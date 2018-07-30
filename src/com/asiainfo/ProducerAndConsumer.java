package com.asiainfo;

/**
 * wait和notify的应用之一——生产者和消费者
 * 
 * @author zhangzhiwang
 * @date 2018年7月26日 下午2:01:13
 */
public class ProducerAndConsumer {
	private static final int MAX_COUNT = 10;
	private volatile int count;

	public synchronized void produce() throws InterruptedException {
		String cur = Thread.currentThread().getName();

		while (count >= MAX_COUNT) {//这里不要用if要用while，因为如果用if判断，虽然wait被唤醒后会进入就需状态，但当该线程获取到CPU时间后会接着上次中断的地方继续往下运行，即执行count++，这样很可能会超出MAX_COUNT；正确的做法是当被唤醒后重新判断count和MAX_COUNT的关系，所以用while
			System.out.println("生产者" + cur + "等待...");
			wait();
		}
		count++;
//		Thread.sleep(100);
		System.out.println("生产者" + cur + "生产了一个产品，当前库存为：" + count);
		notifyAll();
	}

	public synchronized void consume() throws InterruptedException {
		String cur = Thread.currentThread().getName();
		while (count <= 0) {
			System.out.println("消费者" + cur + "等待...");
			wait();
		}
		count--;
//		Thread.sleep(100);
		System.out.println("消费者" + cur + "消费了一个产品，当前库存为：" + count);
		notifyAll();
	}
	
	public static void main(String[] args) {
		ProducerAndConsumer pac = new ProducerAndConsumer();

		//生产者
		for(int i = 0; i < 5; i++) {
			new Thread(new Producer(pac)).start();
//			new Thread(new Consumer(pac)).start();
		}
		
		//消费者
		for(int i = 0; i < 3; i++) {
			new Thread(new Consumer(pac)).start();
		}
	}
}

class Producer implements Runnable {
	private ProducerAndConsumer pac;

	public Producer() {
		super();
	}

	public Producer(ProducerAndConsumer pac) {
		super();
		this.pac = pac;
	}

	@Override
	public void run() {
		for (;;) {
			try {
				pac.produce();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	private ProducerAndConsumer pac;

	public Consumer() {
		super();
	}

	public Consumer(ProducerAndConsumer pac) {
		super();
		this.pac = pac;
	}

	@Override
	public void run() {
		for (;;) {
			try {
				pac.consume();
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}