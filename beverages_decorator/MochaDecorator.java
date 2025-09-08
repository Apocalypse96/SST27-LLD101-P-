package beverages_decorator;

public class MochaDecorator extends BeverageDecorator {

	private final int mochaCost;

	public MochaDecorator(Beverage wrapped) {
		this(wrapped, 4);
	}

	public MochaDecorator(Beverage wrapped, int mochaCost) {
		super(wrapped);
		this.mochaCost = mochaCost;
	}

	@Override
	public int cost() {
		return wrapped.cost() + mochaCost;
	}
}


