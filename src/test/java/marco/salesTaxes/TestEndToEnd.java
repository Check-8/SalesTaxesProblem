package marco.salesTaxes;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import marco.salesTaxes.context.ApplicationContext;
import marco.salesTaxes.product.ProductTypes;
import marco.salesTaxes.tax.Tax;
import marco.salesTaxes.tax.commonPredicate.ApplyOn;
import marco.salesTaxes.tax.commonPredicate.Except;

public class TestEndToEnd {
	private static final String MUS = "music";
	private static final String FOOD = "food";
	private static final String BOOK = "book";
	private static final String MEDS = "medical product";
	private static final String PERF = "perfume";

	private static final String Input_1 = "1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85";
	private static final String Input_2 = "1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50";
	private static final String Input_3 = "1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25";
	private static final String Input_4 = "2 book at 12.49\n1 imported bottle of perfume at 27.99\n3 chocolate bar at 0.85\n2 box of imported chocolates at 11.25";
	private InputLoader il;
	private OutputGenerator og;
	private Collection<marco.salesTaxes.tax.Tax> taxes;

	@Before
	public void setUp() throws Exception {
		ProductTypes pt = ApplicationContext.getProductTypes();
		pt.save("chocolate bar", Arrays.asList(FOOD));
		pt.save("book", Arrays.asList(BOOK));
		pt.save("music CD", Arrays.asList(MUS));
		pt.save("box of chocolates", Arrays.asList(FOOD));
		pt.save("bottle of perfume", Arrays.asList(PERF));
		pt.save("packet of headache pills", Arrays.asList(MEDS));

		il = new InputLoader();
		og = new OutputGenerator();
		Tax basicSalesTax = new Tax("Basic sales tax", 10, new Except("book", "food", "medical product"));
		Tax importTax = new Tax("Import tax", 5, new ApplyOn("imported"));
		taxes = Arrays.asList(basicSalesTax, importTax);
	}

	@Test
	public void testInput1() {
		byte[] byteArray = Input_1.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		List<ProductWithQuantity> input = il.readLines(bais);

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
	public void testInput2() {
		byte[] byteArray = Input_2.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		List<ProductWithQuantity> input = il.readLines(bais);

		List<String> output = og.makeOutput(input, taxes);

		List<String> expected = new ArrayList<>();
		expected.add("1 imported box of chocolates: 10.50");
		expected.add("1 imported bottle of perfume: 54.65");
		expected.add("Sales Taxes: 7.65");
		expected.add("Total: 65.15");
		assertEquals(expected, output);
	}

	@Test
	public void testInput3() {
		byte[] byteArray = Input_3.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		List<ProductWithQuantity> input = il.readLines(bais);

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

	@Test
	public void testInput4() {
		byte[] byteArray = Input_4.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		List<ProductWithQuantity> input = il.readLines(bais);

		List<String> output = og.makeOutput(input, taxes);

		List<String> expected = new ArrayList<>();
		expected.add("2 book: 24.98");
		expected.add("1 imported bottle of perfume: 32.19");
		expected.add("3 chocolate bar: 2.55");
		expected.add("2 imported box of chocolates: 23.70");
		expected.add("Sales Taxes: 5.40");
		expected.add("Total: 83.42");
		assertEquals(expected, output);
	}

}
