package marco.salesTaxes.product;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Product {
	private String name;
	private Collection<String> types;
	private double pricePerUnit;

	public Product(String name, double pricePerUnit, Collection<String> types) {
		super();
		this.name = name;
		this.types = new HashSet<>(types);
		this.pricePerUnit = pricePerUnit;
	}

	public Product(String name, double pricePerUnit, String... types) {
		super();
		this.name = name;
		this.types = new HashSet<>(Arrays.asList(types));
		this.pricePerUnit = pricePerUnit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<String> getTypes() {
		return types;
	}

	public void setTypes(Collection<String> types) {
		this.types = types;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", types=" + types + ", pricePerUnit=" + pricePerUnit + "]";
	}

}
