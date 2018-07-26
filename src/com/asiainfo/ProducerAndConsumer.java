package com.asiainfo;

/**
 * wait和notify的应用之一——生产者和消费者
 * 
 * @author zhangzhiwang
 * @date 2018年7月26日 下午2:01:13
 */
public class ProducerAndConsumer {
	private static final int MAX_COUNT = 100;
	private int count;

	public synchronized void produce() throws InterruptedException {
		String cur = Thread.currentThread().getName();

		if (count >= MAX_COUNT) {
			System.out.println("生产者" + cur + "等待...");
			wait();
		}
		count++;
		System.out.println("生产者" + cur + "生产了一个产品，当前库存为：" + count);
	}

	public synchronized void consume() throws InterruptedException {
		String cur = Thread.currentThread().getName();
		if (count <= 0) {
			System.out.println("消费者" + cur + "等待...");
			wait();
		}
		count--;
		System.out.println("消费者" + cur + "消费了一个产品，当前库存为：" + count);
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
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}