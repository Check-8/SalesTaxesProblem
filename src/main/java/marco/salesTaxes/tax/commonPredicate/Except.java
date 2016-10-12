package marco.salesTaxes.tax.commonPredicate;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

import marco.salesTaxes.product.Product;

public class Except implements Predicate<Product> {
	private Collection<String> exception;

	public Except(Collection<String> exception) {
		super();
		this.exception = new HashSet<>(exception);
	}

	public Except(String... exception) {
		super();
		this.exception = new HashSet<>(Arrays.asList(exception));
	}

	@Override
	public boolean test(Product t) {
		for (String tag : t.getTypes())
			if (exception.contains(tag))
				return false;
		return true;
	}

}
