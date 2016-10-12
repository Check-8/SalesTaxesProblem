package marco.salesTaxes.tax.commonPredicate;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.function.Predicate;

import marco.salesTaxes.product.Product;

public class ApplyOn implements Predicate<Product> {
	private Collection<String> applicableTo;

	public ApplyOn(Collection<String> applicableTo) {
		super();
		this.applicableTo = new HashSet<>(applicableTo);
	}

	public ApplyOn(String... applicableTo) {
		super();
		this.applicableTo = new HashSet<>(Arrays.asList(applicableTo));
	}

	@Override
	public boolean test(Product t) {
		for (String tag : t.getTypes())
			if (applicableTo.contains(tag))
				return true;
		return false;
	}

}
