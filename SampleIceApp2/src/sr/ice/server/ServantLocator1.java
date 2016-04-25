package sr.ice.server;

import Demo.CategoryName;
import Ice.*;
import Ice.Object;
import sr.ice.ObjectHolder;
import sr.ice.impl.ProductI;

public class ServantLocator1 extends AbstractServantLocator
{

	private final ObjectAdapter adapter;
	private final ObjectHolder holder = new ObjectHolder();

	private int id = 0;

	public ServantLocator1(ObjectAdapter adapter) {
		System.out.println("## ServantLocator1 created ##");

		this.adapter = adapter;
	}

	public Object locate(Current current, LocalObjectHolder cookie) throws UserException {
		System.out.println("## ServantLocator1.locate() ##");

		Identity identity = new Identity(current.id.name, current.id.category);

		Object servant = adapter.find(identity);

		if (servant == null) {
			servant = holder.deserialize(current.id.name);

			if (servant == null) {
				servant = new ProductI(id++, CategoryName.K1);
			}

			System.out.println("servantId " + id);
			adapter.add(servant, new Identity(current.id.name, current.id.category));
		}

		return servant;
	}

}
