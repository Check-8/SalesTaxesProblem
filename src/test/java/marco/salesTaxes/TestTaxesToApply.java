package marco.salesTaxes;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import marco.salesTaxes.product.Product;
import marco.salesTaxes.tax.Tax;
import marco.salesTaxes.tax.commonPredicate.ApplyOn;
import marco.salesTaxes.tax.commonPredicate.Except;

public class TestTaxesToApply {
	private TaxesToApply tta;

	private Tax basicSalesTax;
	private Tax importTax;

	private Product book;
	private Product musicCD;
	private Product importedBottleOfPerfume;

	@Before
	public void setUp() throws Exception {
		tta = new TaxesToApply();
		basicSalesTax = new Tax("Basic sales tax", 10, new Except("book", "food", "medical product"));
		importTax = new Tax("Import tax", 5, new ApplyOn("imported"));

		book = new Product("Book", 12.49, "book");
		musicCD = new Product("Music CD", 14.99, "music");
		importedBottleOfPerfume = new Product("imported bottle of perfume", 47.50, "perfume", "imported");
	}

	@Test
	public void testApplyOneProduct1() {
		Collection<Tax> taxes = Arrays.asList(basicSalesTax, importTax);
		Collection<Tax> results = tta.generateTaxesToApply(book, taxes);
		Collection<Tax> expected = new HashSet<>();

		assertEquals(expected, results);
	}

	@Test
	public void testApplyOneProduct2() {
		Collection<Tax> taxes = Arrays.asList(basicSalesTax, importTax);
		Collection<Tax> results = tta.generateTaxesToApply(musicCD, taxes);
		Collection<Tax> expected = new HashSet<>(Arrays.asList(basicSalesTax));

		assertEquals(expected, results);
	}

	@Test
	public void testApplyOneProduct3() {
		Collection<Tax> taxes = Arrays.asList(basicSalesTax, importTax);
		Collection<Tax> results = tta.generateTaxesToApply(importedBottleOfPerfume, taxes);
		Collection<Tax> expected = new HashSet<>(Arrays.asList(basicSalesTax, importTax));

		assertEquals(expected, results);
	}

}
