package marco.salesTaxes;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import marco.salesTaxes.context.ApplicationContext;
import marco.salesTaxes.product.Product;
import marco.salesTaxes.product.ProductTypes;

public class TestInputLoader {
	private static final String IMP = "imported";
	private static final String MUS = "music";
	private static final String FOOD = "food";
	private static final String BOOK = "book";
	private static final String MEDS = "medical product";
	private static final String PERF = "perfume";

	private InputLoader il;

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
	}

	@Test
	public void testReadLines1() {
		List<ProductWithQuantity> list = null;
		list = il.readLines(Arrays.asList("1 book at 12.49", "1 music CD at 14.99", "1 chocolate bar at 0.85"));

		List<ProductWithQuantity> expected = new ArrayList<>();
		expected.add(new ProductWithQuantity(new Product("book", 12.49, BOOK), 1));
		expected.add(new ProductWithQuantity(new Product("music CD", 14.99, MUS), 1));
		expected.add(new ProductWithQuantity(new Product("chocolate bar", 0.85, FOOD), 1));

		assertEquals(expected, list);
	}

	@Test
	public void testReadLines2() {
		List<ProductWithQuantity> list = null;
		list = il.readLines(
				Arrays.asList("1 imported box of chocolates at 10.00", "1 imported bottle of perfume at 47.50"));

		List<ProductWithQuantity> expected = new ArrayList<>();
		expected.add(new ProductWithQuantity(new Product("imported box of chocolates", 10.00, FOOD, IMP), 1));
		expected.add(new ProductWithQuantity(new Product("imported bottle of perfume", 47.50, PERF, IMP), 1));

		assertEquals(expected, list);
	}

	@Test
	public void testReadLines3() {
		List<ProductWithQuantity> list = null;
		list = il.readLines(Arrays.asList("1 imported bottle of perfume at 27.99", "1 bottle of perfume at 18.99",
				"1 packet of headache pills at 9.75", "1 box of imported chocolates at 11.25"));

		List<ProductWithQuantity> expected = new ArrayList<>();
		expected.add(new ProductWithQuantity(new Product("imported bottle of perfume", 27.99, PERF, IMP), 1));
		expected.add(new ProductWithQuantity(new Product("bottle of perfume", 18.99, PERF), 1));
		expected.add(new ProductWithQuantity(new Product("packet of headache pills", 9.75, MEDS), 1));
		expected.add(new ProductWithQuantity(new Product("imported box of chocolates", 9.75, FOOD, IMP), 1));

		assertEquals(expected, list);
	}

	@Test
	public void testReadLinesInputStream1() {
		List<ProductWithQuantity> list = null;
		byte[] byteArray = new String("1 book at 12.49\n1 music CD at 14.99\n1 chocolate bar at 0.85").getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		list = il.readLines(bais);

		List<ProductWithQuantity> expected = new ArrayList<>();
		expected.add(new ProductWithQuantity(new Product("book", 12.49, BOOK), 1));
		expected.add(new ProductWithQuantity(new Product("music CD", 14.99, MUS), 1));
		expected.add(new ProductWithQuantity(new Product("chocolate bar", 0.85, FOOD), 1));

		assertEquals(expected, list);
	}

	@Test
	public void testReadLinesInputStream2() {
		List<ProductWithQuantity> list = null;
		byte[] byteArray = new String("1 imported box of chocolates at 10.00\n1 imported bottle of perfume at 47.50")
				.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		list = il.readLines(bais);

		List<ProductWithQuantity> expected = new ArrayList<>();
		expected.add(new ProductWithQuantity(new Product("imported box of chocolates", 10.00, FOOD, IMP), 1));
		expected.add(new ProductWithQuantity(new Product("imported bottle of perfume", 47.50, PERF, IMP), 1));

		assertEquals(expected, list);
	}

	@Test
	public void testReadLinesInputStream3() {
		List<ProductWithQuantity> list = null;
		byte[] byteArray = new String(
				"1 imported bottle of perfume at 27.99\n1 bottle of perfume at 18.99\n1 packet of headache pills at 9.75\n1 box of imported chocolates at 11.25")
						.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		list = il.readLines(bais);

		List<ProductWithQuantity> expected = new ArrayList<>();
		expected.add(new ProductWithQuantity(new Product("imported bottle of perfume", 27.99, PERF, IMP), 1));
		expected.add(new ProductWithQuantity(new Product("bottle of perfume", 18.99, PERF), 1));
		expected.add(new ProductWithQuantity(new Product("packet of headache pills", 9.75, MEDS), 1));
		expected.add(new ProductWithQuantity(new Product("imported box of chocolates", 9.75, FOOD, IMP), 1));

		assertEquals(expected, list);
	}

}
