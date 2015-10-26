package controllers;


import is.rufan.user.service.UserService;
import is.rufan.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.details;
import views.html.profile;


import static play.data.Form.form;

import static play.libs.Json.toJson;

public class UserController extends Controller
{
  protected ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/userapp.xml");


  public Result blankprofile()
  {
    String username = session().get("username");
    UserService userService = (UserService)ctx.getBean("userService");

    int id = (Integer.valueOf(session().get("userid"))).intValue();
    User user = userService.getUser(id);

    Form<User> profileForm = form(User.class);
    profileForm = profileForm.fill(user);

    return ok(profile.render(profileForm));
  }

  public Result submit()
  {
    Form<User> profileForm = form(User.class);
    Form<User> updatedForm = profileForm.bindFromRequest();

    if (updatedForm.field("username").value().length () < 4)
    {
      updatedForm.reject("username", "Username must be at least 4 characters");
    }

    if (updatedForm.hasErrors())
    {
      return badRequest(profile.render(updatedForm));
    }
    else
    {
      User user = updatedForm.get();
      UserService service = (UserService) ctx.getBean("userService");
      //service.updateUser(user);

      return ok(profile.render(updatedForm));
    }
  }

  public Result getUser(String username)
  {
    UserService userService = (UserService)ctx.getBean("userService");
    User user = userService.getUserByUsername(username);
    return ok(toJson(user));
  }

  public Result blankDetails()
  {
    return ok(details.render());
  }


}
