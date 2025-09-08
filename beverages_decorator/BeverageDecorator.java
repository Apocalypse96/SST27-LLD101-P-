package beverages_decorator;

public abstract class BeverageDecorator extends Beverage {

	protected final Beverage wrapped;

	protected BeverageDecorator(Beverage wrapped) {
		if (wrapped == null) {
			throw new IllegalArgumentException("Wrapped beverage must not be null");
		}
		this.wrapped = wrapped;
	}
}


