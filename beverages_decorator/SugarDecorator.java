package beverages_decorator;

public class SugarDecorator extends BeverageDecorator {

	private final int sugarCost;

	public SugarDecorator(Beverage wrapped) {
		this(wrapped, 1);
	}

	public SugarDecorator(Beverage wrapped, int sugarCost) {
		super(wrapped);
		this.sugarCost = sugarCost;
	}

	@Override
	public int cost() {
		return wrapped.cost() + sugarCost;
	}
}


