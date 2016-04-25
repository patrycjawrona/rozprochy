package sr.ice.server;

import Ice.Current;
import Ice.Object;
import Ice.UserException;

public abstract class AbstractServantLocator implements Ice.ServantLocator
{

	public void finished(Current current, Object servant, java.lang.Object cookie) throws UserException
	{
		System.out.println("## ServantLocator.finished() ##");
	}

	public void deactivate(String category)
	{
		System.out.println("## ServantLocator.deactivate() ##");
	}
}
