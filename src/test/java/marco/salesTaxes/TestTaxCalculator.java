package marco.salesTaxes;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import marco.salesTaxes.tax.Tax;
import marco.salesTaxes.tax.commonPredicate.ApplyOn;
import marco.salesTaxes.tax.commonPredicate.Except;

public class TestTaxCalculator {

	private static final double DELTA = 0.001d;

	private TaxCalculator tc;
	private Tax basicSalesTax;
	private Tax importTax;

	@Before
	public void setUp() throws Exception {
		tc = new TaxCalculator();
		basicSalesTax = new Tax("Basic sales tax", 10, new Except("book", "food", "medical product"));
		importTax = new Tax("Import tax", 5, new ApplyOn("import"));
	}

	@Test
	public void testOnePriceOneTax1() {
		double price = 14.99;
		Collection<Tax> taxes = Arrays.asList(basicSalesTax);
		double result = tc.applyTaxes(price, taxes);
		double expected = 16.49;
		assertEquals(expected, result, DELTA);
	}

	@Test
	public void testOnePriceOneTax2() {
		double price = 10.00;
		Collection<Tax> taxes = Arrays.asList(importTax);
		double result = tc.applyTaxes(price, taxes);
		double expected = 10.50;
		assertEquals(expected, result, DELTA);
	}

	@Test
	public void testOnePriceOneTax3() {
		double price = 18.99;
		Collection<Tax> taxes = Arrays.asList(basicSalesTax);
		double result = tc.applyTaxes(price, taxes);
		double expected = 20.89;
		assertEquals(expected, result, DELTA);
	}

	@Test
	public void testOnePriceOneTax4() {
		double price = 11.25;
		Collection<Tax> taxes = Arrays.asList(importTax);
		double result = tc.applyTaxes(price, taxes);
		double expected = 11.85;
		assertEquals(expected, result, DELTA);
	}

	@Test
	public void testOnePriceTwoTaxes1() {
		double price = 47.5;
		Collection<Tax> taxes = Arrays.asList(basicSalesTax, importTax);
		double result = tc.applyTaxes(price, taxes);
		double expected = 54.65;
		assertEquals(expected, result, DELTA);
	}

	@Test
	public void testOnePriceTwoTaxes2() {
		double price = 27.99;
		Collection<Tax> taxes = Arrays.asList(basicSalesTax, importTax);
		double result = tc.applyTaxes(price, taxes);
		double expected = 32.19;
		assertEquals(expected, result, DELTA);
	}

}
