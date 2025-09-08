package beverages_decorator;

public class Client {

	public static void main(String[] args) {
		Beverage cappuccino = new Cappuccino();
		System.out.println("Plain Cappuccino cost:");
		System.out.println(cappuccino.cost());

		Beverage latte = new Latte();
		System.out.println("Plain Latte cost:");
		System.out.println(latte.cost());

		Beverage cappuccinoWithMilk = new MilkDecorator(cappuccino);
		System.out.println("Cappuccino + Milk cost:");
		System.out.println(cappuccinoWithMilk.cost());

		Beverage latteWithMocha = new MochaDecorator(latte);
		System.out.println("Latte + Mocha cost:");
		System.out.println(latteWithMocha.cost());

		Beverage fullLoaded = new SugarDecorator(new MochaDecorator(new MilkDecorator(new Cappuccino())));
		System.out.println("Cappuccino + Milk + Mocha + Sugar cost:");
		System.out.println(fullLoaded.cost());
	}

}