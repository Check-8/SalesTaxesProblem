package marco.salesTaxes.context;

import marco.salesTaxes.product.ProductTypes;

public class ApplicationContext {
	private static ProductTypes types = new ProductTypes();

	public static ProductTypes getProductTypes() {
		return types;
	}

}
