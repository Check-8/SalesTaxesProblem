package marco.salesTaxes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import marco.salesTaxes.product.Product;
import marco.salesTaxes.tax.Tax;
import marco.salesTaxes.tax.commonPredicate.ApplyOn;
import marco.salesTaxes.tax.commonPredicate.Except;

public class TestOutputGenerator {
	private static final String IMP = "imported";
	private static final String MUS = "music";
	private static final String FOOD = "food";
	private static final String BOOK = "book";
	private static final String MEDS = "medical product";
	private static final String PERF = "perfume";

	private OutputGenerator og;
	private Collection<Tax> taxes;

	@Before
	public void setUp() throws Exception {
		og = new OutputGenerator();

		Tax basicSalesTax = new Tax("Basic sales tax", 10, new Except("book", "food", "medical product"));
		Tax importTax = new Tax("Import tax", 5, new ApplyOn("imported"));
		taxes = Arrays.asList(basicSalesTax, importTax);

	}

	@Test
	public void testGenerateOutput1() {
		List<ProductWithQuantity> input = new ArrayList<>();
		input.add(new ProductWithQuantity(new Product("book", 12.49, BOOK), 1));
		input.add(new ProductWithQuantity(new Product("music CD", 14.99, MUS), 1));
		input.add(new ProductWithQuantity(new Product("chocolate bar", 0.85, FOOD), 1));

		List<String> output = og.makeOutput(input, taxes);

		List<String> expected = new ArrayList<>();
		expected.add("1 book: 12.49");
		expected.add("1 music CD: 16.49");
		expected.add("1 chocolate bar: 0.85");
		expected.add("Sales Taxes: 1.50");
		expected.add("Total: 29.83");
		assertEquals(expected, output);
	}

	@Test
	public void testGenerateOutput2() {
		List<ProductWithQuantity> input = new ArrayList<>();
		input.add(new ProductWithQuantity(new Product("imported box of chocolates", 10.00, FOOD, IMP), 1));
		input.add(new ProductWithQuantity(new Product("imported bottle of perfume", 47.50, PERF, IMP), 1));

		List<String> output = og.makeOutput(input, taxes);

		List<String> expected = new ArrayList<>();
		expected.add("1 imported box of chocolates: 10.50");
		expected.add("1 imported bottle of perfume: 54.65");
		expected.add("Sales Taxes: 7.65");
		expected.add("Total: 65.15");
		assertEquals(expected, output);
	}

	@Test
	public void testGenerateOutput3() {
		List<ProductWithQuantity> input = new ArrayList<>();
		input.add(new ProductWithQuantity(new Product("imported bottle of perfume", 27.99, PERF, IMP), 1));
		input.add(new ProductWithQuantity(new Product("bottle of perfume", 18.99, PERF), 1));
		input.add(new ProductWithQuantity(new Product("packet of headache pills", 9.75, MEDS), 1));
		input.add(new ProductWithQuantity(new Product("imported box of chocolates", 11.25, FOOD, IMP), 1));

		List<String> output = og.makeOutput(input, taxes);

		List<String> expected = new ArrayList<>();
		expected.add("1 imported bottle of perfume: 32.19");
		expected.add("1 bottle of perfume: 20.89");
		expected.add("1 packet of headache pills: 9.75");
		expected.add("1 imported box of chocolates: 11.85");
		expected.add("Sales Taxes: 6.70");
		expected.add("Total: 74.68");
		assertEquals(expected, output);
	}

}
