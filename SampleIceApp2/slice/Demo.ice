
#ifndef SR_DEMO_ICE
#define SR_DEMO_ICE

module Demo
{

    enum CategoryName {K1, K2, K3, K4, K5};

	interface Product
    	{
    		long getId();
    		CategoryName getCategory();
    		string getInfo();
            string getWhenCreated();
    	};

};

#endif
