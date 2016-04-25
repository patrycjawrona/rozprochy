// **********************************************************************
//
// Copyright (c) 2003-2011 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

package sr.ice.server;

import Demo.CategoryName;
import Ice.ServantLocator;
import sr.ice.ObjectHolder;
import sr.ice.impl.ProductI;

public class Server
{
	public void t1(String[] args)
	{
		int status = 0;

		ObjectHolder holder = new ObjectHolder();

		Ice.Communicator communicator = null;

		try
		{
			communicator = Ice.Util.initialize(args);

			Ice.ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("Adapter1",
				"tcp -h 0.0.0.0 -p 10000:udp -h 0.0.0.0 -p 10000");


			ProductI product1 = new ProductI(1, CategoryName.K1);
			ProductI product2 = new ProductI(2, CategoryName.K2);
			ProductI product3 = new ProductI(3, CategoryName.K3);
			ProductI product4 = new ProductI(4, CategoryName.K4);
			ProductI product5 = new ProductI(5, CategoryName.K5);

			holder.serialize(product1, "product1");
			holder.serialize(product2, "product2");
			holder.serialize(product3, "product3");
			holder.serialize(product4, "product4");
			holder.serialize(product5, "product5");

			ServantLocator locator1 = new ServantLocator1(adapter);
			ServantLocator locator2 = new ServantLocator2();
			ServantLocator locator3 = new ServantLocator3();
			System.out.println("## ServantLocator4 created ##");
			ServantLocator evictor5 = new ServantLocator5();

			adapter.addServantLocator(locator1, "K1");
			adapter.addServantLocator(locator2, "K2");
			adapter.addServantLocator(locator3, "K3");
			adapter.addDefaultServant(product4, "K4");
			adapter.addServantLocator(evictor5, "K5");

			adapter.activate();
			System.out.println("Entering event processing loop...");
			communicator.waitForShutdown();
		}
		catch (Exception e)
		{
			System.err.println(e);
			status = 1;
		}
		if (communicator != null)
		{
			// Clean up
			try
			{
				communicator.destroy();
			}
			catch (Exception e)
			{
				System.err.println(e);
				status = 1;
			}
		}
		System.exit(status);
	}


	public static void main(String[] args)
	{
		Server app = new Server();
		app.t1(args);
	}
}
