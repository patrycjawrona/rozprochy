package sr.ice;

import Ice.Object;

import java.io.*;

public class ObjectHolder implements Serializable {

    private static final String repoLocation = "src/resources/";

    private File resource;

    public ObjectHolder() {
        this.resource = new File(repoLocation);
    }

    public void serialize(Object object, String name){

        try {
            FileOutputStream fileOut = new FileOutputStream(getFileName(name));

            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            objectOut.close();
            fileOut.close();

            System.out.println("Object " + name + " serialized");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object deserialize(String name){

        Object object = null;

        try
        {
            FileInputStream fileIn = new FileInputStream(getFileName(name));
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            object = (Object) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        }catch(IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return object;
    }

    private File getFileName(String key) {
        return new File(resource, key);
    }
}
