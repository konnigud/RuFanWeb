package is.rufan.user.test;

import is.rufan.user.data.UserDuplicateException;
import is.rufan.user.domain.User;
import is.rufan.user.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserTest
{
  public static void main(String[] args)
  {
    new UserTest();
  }

  public UserTest()
  {
    // get the service
    ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:userapp.xml");
    UserService userService = (UserService) applicationContext.getBean("userService");

    // Add new user, a typical Liverpool fan
    User user = new User("Michael Shields", "mshields", "mshields@hotmail.com", "anfield",false);
    userService.addUser(user);

    User newUser = userService.getUser(3);

    // should throw duplicate exception
    try
    {
      userService.addUser(user);
    }
    catch(UserDuplicateException udex)
    {
      System.out.println("Username taken!");
    }
  }
}
