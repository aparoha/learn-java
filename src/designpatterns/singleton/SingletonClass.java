package designpatterns.singleton;

import java.io.*;

/*
        1. Creational design pattern

 */
public class SingletonClass implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;
    private static volatile SingletonClass sSoleInstance;

    //private constructor.
    private SingletonClass(){

        //Prevent form the reflection api.
        if (sSoleInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static SingletonClass getInstance() {
        if (sSoleInstance == null) { //if there is no instance available... create new one
            synchronized (SingletonClass.class) {
                if (sSoleInstance == null) sSoleInstance = new SingletonClass();
            }
        }

        return sSoleInstance;
    }

    //Make singleton from serialize and deserialize operation.
    protected Object readResolve() throws ObjectStreamException {
        return getInstance();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return getInstance();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        //Instance 1
        SingletonClass instance1 = SingletonClass.getInstance();

        //Instance 2
        SingletonClass instance2 = SingletonClass.getInstance();

        //now lets check the hash key.
        System.out.println("Instance 1 hash:" + instance1.hashCode());
        System.out.println("Instance 2 hash:" + instance2.hashCode());

        try {
            SingletonClass instance3 = SingletonClass.getInstance();
            ObjectOutput out = null;

            out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
            out.writeObject(instance3);
            out.close();

            //deserialize from file to object
            ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
            SingletonClass instance4 = (SingletonClass) in.readObject();
            in.close();

            System.out.println("Instance 3 hash:" + instance3.hashCode());
            System.out.println("Instance 4 hash:" + instance4.hashCode());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        SingletonClass instance5 = SingletonClass.getInstance();
        SingletonClass instance6 = (SingletonClass) instance5.clone();
        System.out.println("Instance 5 hash:" + instance5.hashCode());
        System.out.println("Instance 6 hash:" + instance6.hashCode());
    }
}
