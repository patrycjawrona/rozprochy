package sr.ice.server;

import Demo.CategoryName;
import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import Ice.UserException;
import sr.ice.impl.ProductI;

public class ServantLocator3 extends AbstractServantLocator
{
	private static final int N = 5;

	private ProductI[] servants;

	public ServantLocator3() {
		System.out.println("## ServantLocator3 created ##");
		this.servants = new ProductI[N];
	}

	public Object locate(Current current, LocalObjectHolder cookie) throws UserException {
		System.out.println("## ServantLocator3.locate() ##");

		int i = findLeastRecentServant(servants);
		ProductI servant = servants[i];

		if (servant == null) {
			servant = new ProductI(i, CategoryName.K3);
			System.out.println("servantId " + i);
			servants[i] = servant;
		}

		return servant;
	}

	public static int findLeastRecentServant(ProductI[] servants) {
		int index = 0;
		long whenCreatedMax = Long.MAX_VALUE;
		for (int i = 0; i < servants.length; i++) {
			if (servants[i] != null) {
				if (whenCreatedMax > servants[i].whenCreated) {
					index = i;
					whenCreatedMax = servants[i].whenCreated;
				}
			} else {
				index = i;
				whenCreatedMax = 0;
			}
		}

		return index;
	}
}
