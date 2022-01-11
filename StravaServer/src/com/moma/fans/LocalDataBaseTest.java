package com.moma.fans;

import com.moma.fans.data.dao.UserDAO;
import com.moma.fans.data.domain.LocalUser;
import com.moma.fans.data.domain.User;

public class LocalDataBaseTest {

    public static void main(String [] args) {

        LocalUser lu = new LocalUser("test@gmail.com", "Test", "Hola");
        System.out.println("Guardando usuario local...");
        UserDAO.getInstance().save(lu);
        System.out.println("Recuperando usuario local...");
        User uDB = UserDAO.getInstance().getByEmail("test@gmail.com");
        System.out.println("Classloader DB: " + uDB.getClass().getClassLoader());
        System.out.println("Classloader actual: " + Thread.currentThread().getContextClassLoader());



        try {

           System.out.println("Contraseña: " + ((LocalUser) uDB).getPassword());

       }

       catch (Exception e) {

           e.printStackTrace();
       }

       System.out.println("Borrando usuario de DB...");
       UserDAO.getInstance().deleteObject(uDB);

       User j = UserDAO.getInstance().getByEmail("jonan@gmail.com");
       System.out.println(j.getClass());
    }
}
