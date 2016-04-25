package sr.ice.impl;

import Demo.CategoryName;
import Demo._ProductDisp;
import Ice.Current;

import java.util.Calendar;
import java.util.Date;

public class ProductI extends _ProductDisp {

    private long id;
    private CategoryName name;
    public long whenCreated;

    public ProductI(long id, CategoryName name){
        this.id = id;
        this.name = name;
        this.whenCreated = new Date().getTime();
    }

    @Override
    public long getId(Current __current) {
        return id;
    }

    @Override
    public CategoryName getCategory(Current __current) {
        return name;
    }

    @Override
    public String getInfo(Current __current) {
        return "Object #" + id + " of category " + name;
    }

    @Override
    public String getWhenCreated(Current __current) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(whenCreated);

        int mHours = calendar.get(Calendar.HOUR);
        int mMinutes = calendar.get(Calendar.MINUTE);
        int mSeconds = calendar.get(Calendar.SECOND);
        return mHours + ":" + mMinutes + ":" + mSeconds;
    }
}
