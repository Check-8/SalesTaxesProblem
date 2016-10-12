package marco.salesTaxes.product;

import java.util.Collection;

public interface IProductTypes {

	public Collection<String> getTypes(String name);

	public void save(String name, Collection<String> types);

}
