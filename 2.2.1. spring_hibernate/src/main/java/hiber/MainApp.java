package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "LastName1","User1@gmail.com");
      Car car1 = new Car("Subaru",3);
      User user2 = new User("User2","LastName2","User2@gmail.com");
      Car car2 = new Car("Toyota", 10);

      userService.add(user1.setUserCar(car1).setCarUser(user1));
      userService.add(user2.setUserCar(car2).setCarUser(user2));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getUserCar());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Subaru", 3));

      context.close();
   }
}
