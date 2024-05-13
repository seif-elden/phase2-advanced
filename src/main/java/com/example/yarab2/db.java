package com.example.yarab2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class db {

    public static void update(Map object) {
        try {
            FileOutputStream myFileOutStream
                    = new FileOutputStream("C:/Users/DELL/IdeaProjects/yarab2/src/main/java/com/example/yarab2/data.txt");

            ObjectOutputStream myObjectOutStream
                    = new ObjectOutputStream(myFileOutStream);
            System.out.println(object);

            myObjectOutStream.writeObject(object);

            myObjectOutStream.close();
            myFileOutStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
