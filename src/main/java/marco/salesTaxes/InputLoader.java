package marco.salesTaxes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import marco.salesTaxes.context.ApplicationContext;
import marco.salesTaxes.product.Product;
import marco.salesTaxes.product.ProductTypes;

public class InputLoader {

	public List<ProductWithQuantity> readLines(InputStream is) {
		try (Scanner scan = new Scanner(is)) {
			List<ProductWithQuantity> list = new ArrayList<>();
			while (scan.hasNextLine())
				list.add(readLine(scan.nextLine()));
			return list;
		}
	}

	public List<ProductWithQuantity> readLines(List<String> lines) {
		List<ProductWithQuantity> list = new ArrayList<>();
		for (String line : lines)
			list.add(readLine(line));
		return list;
	}

	private ProductWithQuantity readLine(String line) {
		String[] split = line.split(" ", 2);
		String quantityString = split[0];
		Integer quantity = Integer.parseInt(quantityString);

		int index = split[1].lastIndexOf(" at ");

		String name = split[1].substring(0, index);

		String priceString = split[1].substring(index + 4);
		Double price = Double.parseDouble(priceString);

		String nameNoImported = nameNoImported(name);

		Collection<String> types = null;
		ProductTypes pt = ApplicationContext.getProductTypes();
		types = pt.getTypes(nameNoImported);
		if (isImported(name)) {
			name = ("imported " + nameNoImported).replaceAll(" +", " ");
			types.add("imported");
		}

		Product p = new Product(name, price, types);
		return new ProductWithQuantity(p, quantity);
	}

	private static String nameNoImported(String name) {
		String nameNoImported = name.replaceFirst("imported", "");
		return nameNoImported;
	}

	private static boolean isImported(String name) {
		return name.contains("imported");
	}

	
}
