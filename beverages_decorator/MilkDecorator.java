package beverages_decorator;

public class MilkDecorator extends BeverageDecorator {

	private final int milkCost;

	public MilkDecorator(Beverage wrapped) {
		this(wrapped, 3);
	}

	public MilkDecorator(Beverage wrapped, int milkCost) {
		super(wrapped);
		this.milkCost = milkCost;
	}

	@Override
	public int cost() {
		return wrapped.cost() + milkCost;
	}
}


