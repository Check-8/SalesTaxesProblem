package marco.salesTaxes;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import marco.salesTaxes.product.Product;
import marco.salesTaxes.tax.Tax;

public class TaxesToApply {
	public Collection<Tax> generateTaxesToApply(Product p, Collection<Tax> taxes) {
		Collection<Tax> taxesToApply = new HashSet<>();
		for (Tax tax : taxes)
			if (tax.getApplicableTo().test(p))
				taxesToApply.add(tax);
		return taxesToApply;
	}

	public Map<Product, Collection<Tax>> generateTaxesToApply(List<Product> products, Collection<Tax> taxes) {
		Map<Product, Collection<Tax>> product2applicableTax = null;
		product2applicableTax = new HashMap<>();
		for (Product p : products) {
			if (!product2applicableTax.containsKey(p))
				product2applicableTax.put(p, generateTaxesToApply(p, taxes));
		}
		return product2applicableTax;
	}
}
