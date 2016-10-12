package marco.salesTaxes;

import java.util.Collection;

import marco.salesTaxes.tax.Tax;

public class TaxCalculator {

	private static final double ROUND_TO = 0.05d;

	private double roundTo;

	public TaxCalculator() {
		roundTo = ROUND_TO;
	}

	public TaxCalculator(double roundTo) {
		this.roundTo = roundTo;
	}

	public double applyTaxes(double basePrice, Collection<Tax> taxes) {
		double priceWithTaxes = basePrice;
		for (Tax tax : taxes) {
			double calculatedTax = applyTax(basePrice, tax);
			priceWithTaxes += calculatedTax;
		}
		return priceWithTaxes;
	}

	private double applyTax(double price, Tax tax) {
		return roundUp(price * tax.getRate());
	}

	private double roundUp(double toRound) {
		double c0 = toRound / roundTo;
		double c1 = c0 - (long) (c0);
		if (c1 == 0)
			return toRound;
		double c2 = 1 - c1;
		double rounded = (c2 * roundTo) + toRound;
		return rounded;
	}
}
