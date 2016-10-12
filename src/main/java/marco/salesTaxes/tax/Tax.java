package marco.salesTaxes.tax;

import java.util.Objects;
import java.util.function.Predicate;

import marco.salesTaxes.product.Product;

public class Tax {
	private String taxName;
	private double rate;
	private Predicate<Product> applicableTo;

	public Tax(String taxName, double rateInPercentage, Predicate<Product> applicableTo) {
		this.taxName = Objects.requireNonNull(taxName);
		rate = rateInPercentage / 100;
		this.applicableTo = Objects.requireNonNull(applicableTo);
	}

	public String getTaxName() {
		return taxName;
	}

	public double getRate() {
		return rate;
	}

	public Predicate<Product> getApplicableTo() {
		return applicableTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taxName == null) ? 0 : taxName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Tax))
			return false;
		Tax other = (Tax) obj;
		if (taxName == null) {
			if (other.taxName != null)
				return false;
		} else if (!taxName.equals(other.taxName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tax [taxName=" + taxName + "]";
	}

}
