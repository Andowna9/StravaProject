package com.moma.fans;

import com.moma.fans.data.dao.UserDAO;
import com.moma.fans.data.domain.LocalUser;
import com.moma.fans.data.domain.RegisterType;
import com.moma.fans.data.domain.User;
import com.moma.fans.services.UserAppService;

public class LocalDataBaseTest {

    public static void main(String [] args) {



        try {

          /* User userDB = UserDAO.getInstance().getByEmail("jonan@gmail.com");
          System.out.println("DB Classloader: " + userDB.getClass().getClassLoader());
          System.out.println("Current Classloader: " + Thread.currentThread().getContextClassLoader());
          System.out.println("LocalUser Classloader: " + LocalUser.class.getClassLoader());
          System.out.println(((LocalUser) userDB).getPassword()); */

          /* User user = UserAppService.getInstance().registerUser("test@gmail.com", "Test", "Hola", RegisterType.LOCAL);
          System.out.println(user.getClass()); */

          /* User userDB = UserAppService.getInstance().login("test@gmail.com", "Hola");
          System.out.println(userDB.getClass());

          /* UserDAO.getInstance().deleteObject(user); */

         /* UserDAO.getInstance().save(new LocalUser("bleach@gmail.com", "Bleach", "BL"));
          User udb1 = UserDAO.getInstance().getByEmail("bleach@gmail.com");
          User udb2 = UserDAO.getInstance().getByEmail("bleach@gmail.com");
          System.out.println(udb1.getClass());
          System.out.println(udb2.getClass());
          UserDAO.getInstance().deleteObject(udb1); */

          /* User user = UserAppService.getInstance().login("bleach@gmail.com", "BL");
          System.out.println(user); */

          /* Test Roberto */

            /* Register */

            /* User lu = UserAppService.getInstance().registerUser("test@gmail.com", "Test", "Hola", RegisterType.LOCAL);
            System.out.println("Usuario registrado: " + lu); */

           /* User registeredUser = UserAppService.getInstance().registerUser("test@gmail.com", "Test", "Hola", RegisterType.LOCAL);
           System.out.println("Usuario registrado: " + registeredUser.getClass());
           User sessionUser = UserAppService.getInstance().login("test@gmail.com", "Hola");
           System.out.println("Inicio de sesión: " + sessionUser.getClass()); */
            // User uDB = UserDAO.getInstance().getByEmail("test@gmail.com");
            // UserDAO.getInstance().save(new LocalUser("test@gmail.com", "Test", "pass"));
            User user = UserDAO.getInstance().getByEmail("test@gmail.com");
            // System.out.println(user);
            // UserDAO.getInstance().deleteObject(user);


        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
