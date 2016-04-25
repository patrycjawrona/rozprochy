package sr.ice.server;

import Demo.CategoryName;
import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import sr.ice.ObjectHolder;
import sr.ice.evictor.EvictorBase;
import sr.ice.impl.ProductI;

public class ServantLocator5 extends EvictorBase {
	private static final int N = 3;

	private ObjectHolder holder = new ObjectHolder();

	public ServantLocator5() {
		super(N);
		System.out.println("## ServantEvictor5 created ##");
	}

	@Override
	public Object add(Current current, LocalObjectHolder cookie) {
		System.out.println("## ServantEvictor5 add(" + current.id + ") #");

		Object object = holder.deserialize(current.id.name);

		if (object == null) {
			object = new ProductI(0, CategoryName.K5);
		}

		return object;
	}

	@Override
	public void evict(Object servant, java.lang.Object cookie) {
		ProductI product = (ProductI) servant;
		System.out.println("## ServantEvictor5 evict(product" + product.getId() + ") #");

		holder.serialize(product, "product" + product.getId());
	}

}
