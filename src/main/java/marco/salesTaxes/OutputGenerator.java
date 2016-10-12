package marco.salesTaxes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import marco.salesTaxes.product.Product;
import marco.salesTaxes.tax.Tax;

public class OutputGenerator {
	private TaxCalculator tc;

	public OutputGenerator() {
		tc = new TaxCalculator();
	}

	public OutputGenerator(TaxCalculator tc) {
		super();
		this.tc = tc;
	}

	public List<String> makeOutput(List<ProductWithQuantity> productsWQ, Collection<Tax> taxes) {
		TaxesToApply tta = new TaxesToApply();
		List<Product> pp = productsWQ.stream().map(pwq -> pwq.getProduct()).collect(Collectors.toList());
		Map<Product, Collection<Tax>> map = tta.generateTaxesToApply(pp, taxes);

		Map<Product, Double> product2priceWithTaxes = new HashMap<>();
		for (Entry<Product, Collection<Tax>> entry : map.entrySet()) {
			double priceWithTax = tc.applyTaxes(entry.getKey().getPricePerUnit(), entry.getValue());
			product2priceWithTaxes.put(entry.getKey(), priceWithTax);
		}
		List<String> output = new ArrayList<>();
		double totalWithTaxes = 0.0d;
		double total = 0.0d;
		for (ProductWithQuantity pwq : productsWQ) {
			int quantity = pwq.getQuantity();
			double priceWithTax = product2priceWithTaxes.get(pwq.getProduct());
			totalWithTaxes += (priceWithTax * quantity);
			total += (pwq.getProduct().getPricePerUnit() * quantity);
			StringBuilder sb = new StringBuilder();
			sb.append(quantity + " ");
			sb.append(pwq.getProduct().getName());
			sb.append(": ");
			sb.append(formatDouble(priceWithTax * quantity));
			output.add(sb.toString());
		}
		output.add(new String("Sales Taxes: " + formatDouble(totalWithTaxes - total)));
		output.add(new String("Total: " + formatDouble(totalWithTaxes)));

		return output;
	}

	private static String formatDouble(double d) {
		String format = "%3.2f";
		return String.format(Locale.ENGLISH, format, d);
	}
}
