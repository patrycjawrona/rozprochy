// **********************************************************************
//
// Copyright (c) 2003-2011 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

package sr.ice.client;

import Demo.ProductPrx;
import Demo.ProductPrxHelper;
import Ice.LocalException;

public class Client
{
	public static void main(String[] args)
	{
		int status = 0;
		Ice.Communicator communicator = null;

		try {

			communicator = Ice.Util.initialize(args);

			String line, category, product, proxy;
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

			System.out.println("Choose category..");
			System.out.print("==> ");
			System.out.flush();
			category = in.readLine();

			System.out.println("Choose product..");
			System.out.print("==> ");
			System.out.flush();
			product = in.readLine();

			proxy = "K" + category + "/product" + product;
			Ice.ObjectPrx base = communicator.stringToProxy(proxy + ":tcp -h localhost -p 10000:udp -h localhost -p 10000");

			ProductPrx productPrx = ProductPrxHelper.checkedCast(base);
			if (productPrx == null) throw new Error("Invalid proxy");

			do {
				System.out.print("\t==> ");
				System.out.flush();
				line = in.readLine();

				if (line.equals("getId")) {
					System.out.println("\t\tRESULT ProductId is = " + productPrx.getId());
				}

				if (line.equals("getCategory")) {
					System.out.println("\t\tRESULT Product belongs to category " + productPrx.getCategory());
				}

				if (line.equals("getInfo")) {
					System.out.println("\t\tRESULT " + productPrx.getInfo());
				}

				if (line.equals("whenCreated")) {
					System.out.println("\t\tRESULT Product created at " + productPrx.getWhenCreated());
				}
			} while (!line.equals("exit"));

		} catch (LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}
		if (communicator != null) {
			try {
				communicator.destroy();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}

}