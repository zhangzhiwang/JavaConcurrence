package com.asiainfo;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 工厂方法模式——AQS使用的设计模式
 * 
 * @author zhangzhiwang
 * @date 2018年7月24日 下午12:21:34
 */
public abstract class MakeDrinkTemplate {
	/**
	 * 制作饮料
	 * 
	 * @author zhangzhiwang
	 * @date 2018年7月24日 下午12:41:57
	 */
	public final void makeDrink() {// 为什么是final的，就是因为防止子类去任意调换制作饮料个步骤的顺序以及增加或者减少步骤，破坏制作饮料的整体架构
		/**
		 * 制作饮料的通用步骤：1、烧水 2、加主要佐料 3、加辅助佐料 4、倒入杯中</br>
		 * 其中第1、4步是制作任何饮料都需要进行的通用步骤，不可更改，所以是private的；而第2、3步根据具体制作的饮料不同加的佐料会不同，具体的实现要放在子类去完成，所以定义为抽象方法。</br>
		 * makeDrink()方法是此模板的主要方法，它规定了制作一杯饮料的所有步骤，制作任何饮料都要经过这4步，所以不允许子类去重写该方法，所以是final的。</br>
		 * 另外，由于模板既有具体方法又有需要子类去实现的抽象方法，所以模板方法类要是抽象的。
		 */
		boilWater();// 烧水是制作所有饮料必须的步骤，且也不会因为制作不同的饮料而有不同的烧水的方法，就一种烧水的方法，所以该实现只在模板类中定义实现，不允许子类去实现自己的烧水方法，所以该方法对子类不可见，子类也无需关心烧水的方法。
		addMain();
		if (needSub()) {// 钩子方法：默认是需要加辅助佐料的，如果不需要则子类中要重写钩子方法并返回false
			addSub();
		}
		pourToACup();// 制作的具体什么饮料只关心该饮料加什么主佐料和辅助佐料即可，无需关心其他步骤，因此其他步骤对子类不可见
	}

	/**
	 * 烧水
	 * 
	 * @author zhangzhiwang
	 * @date 2018年7月24日 下午12:43:03
	 */
	private void boilWater() {
		System.out.println("烧水");
	}

	/**
	 * 加主佐料
	 * 
	 * @author zhangzhiwang
	 * @date 2018年7月24日 下午12:43:12
	 */
	protected abstract void addMain();

	/**
	 * 加辅助佐料
	 * 
	 * @author zhangzhiwang
	 * @date 2018年7月24日 下午12:44:12
	 */
	protected abstract void addSub();

	/**
	 * 把饮料倒出来
	 * 
	 * @author zhangzhiwang
	 * @date 2018年7月24日 下午12:45:20
	 */
	private void pourToACup() {
		System.out.println("饮料制作完成并倒入杯子中");
	}

	/**
	 * 是否需要加辅助佐料
	 * 
	 * @return
	 * @author zhangzhiwang
	 * @date 2018年7月24日 下午1:00:00
	 */
	public boolean needSub() {
		return true;
	}
	
	public static void main(String[] args) {
		MakeDrinkTemplate makeCoffee = new MakeCoffee();
		makeCoffee.makeDrink();
		System.out.println("---------------------------");
		MakeDrinkTemplate makeGuice = new MakeGuice();
		makeGuice.makeDrink();
	}
}

class MakeCoffee extends MakeDrinkTemplate {

	@Override
	public void addMain() {
		System.out.println("制作咖啡，主佐料加咖啡粉...");
	}

	@Override
	public void addSub() {
		System.out.println("制作咖啡，辅助佐料加伴侣和糖...");
	}
}

class MakeGuice extends MakeDrinkTemplate {

	@Override
	public void addMain() {
		System.out.println("制作果珍，主佐料加果珍粉...");
	}

	@Override
	public void addSub() {//重写了钩子方法并返回false，该方法不会被执行
		System.out.println("制作咖啡，辅助佐料加伴侣和糖...");
	}
	
	public boolean needSub() {
		return false;
	}
}