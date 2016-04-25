package sr.ice.server;

import Demo.CategoryName;
import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import Ice.UserException;
import sr.ice.impl.ProductI;

public class ServantLocator2 extends AbstractServantLocator
{
	private long id = 0;

	public ServantLocator2() {
		System.out.println("## ServantLocator2 created ##");
	}

	public Object locate(Current current, LocalObjectHolder cookie) throws UserException {
		System.out.println("## ServantLocator2.locate() ##");

		System.out.println("servantId " + id);
		return new ProductI(id++, CategoryName.K2);
	}
}
