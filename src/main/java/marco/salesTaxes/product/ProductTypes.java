package marco.salesTaxes.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

public class ProductTypes implements IProductTypes {
	private Map<String, Collection<String>> name2type;

	public ProductTypes() {
		name2type = new HashMap<>();
	}

	@Override
	public Collection<String> getTypes(String name) {
		return new ArrayList<>(name2type.get(formatName(name)));
	}

	@Override
	public void save(String name, Collection<String> types) {
		name2type.put(formatName(name), new HashSet<>(types));
	}

	private static String formatName(String name) {
		return name.trim().replaceAll(" ", "").toUpperCase(Locale.ENGLISH);
	}
}
