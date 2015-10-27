package controllers;


import com.fasterxml.jackson.databind.JsonNode;
import is.rufan.user.service.UserService;
import is.rufan.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.details;



import static play.data.Form.form;

import static play.libs.Json.toJson;

public class UserController extends Controller
{
  protected ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/userapp.xml");


  /*public Result blankprofile()
  {
    String username = session().get("username");
    UserService userService = (UserService)ctx.getBean("userService");

    int id = (Integer.valueOf(session().get("userid"))).intValue();
    User user = userService.getUser(id);

    Form<User> profileForm = form(User.class);
    profileForm = profileForm.fill(user);

    return ok(profile.render(profileForm));
  }*/
  @BodyParser.Of(BodyParser.Json.class)
  public Result update()
  {
    JsonNode json = request().body().asJson();
    String username = session().get("username");


    User updatedUser = new User();

    updatedUser.setUsername(username);
    updatedUser.setName(json.findPath("name").textValue());
    updatedUser.setEmail(json.findPath("email").textValue());
    updatedUser.setFavteam(json.findPath("favteam").textValue());
    updatedUser.setPassword(json.findPath("password").textValue());
    updatedUser.setCreditcard(json.findPath("creditcard").textValue());
    updatedUser.setManager(json.findPath("manager").asBoolean());
    //user.set

    UserService userService = (UserService)ctx.getBean("userService");
    User user = userService.getUserByUsername(username);

    if(updatedUser.getFavteam() != null && updatedUser.getFavteam().isEmpty()){
      updatedUser.setFavteam(user.getFavteam());
    }

    if(updatedUser.getName() == user.getName() &&
       updatedUser.getEmail() == user.getEmail() &&
       updatedUser.getCreditcard() == user.getCreditcard() &&
       updatedUser.getFavteam() == user.getFavteam() &&
       updatedUser.getPassword() == user.getPassword() &&
       updatedUser.isManager() == user.isManager()){

      return ok("No changes");
    }
    if(updatedUser.getCreditcard().length() != 0) {
      if (updatedUser.getCreditcard().contains("[0-9]") == false &&
              updatedUser.getCreditcard().length() != 16) {
          return badRequest("Credit card number is invalid!!!");
      }
    }

    updatedUser.setId(user.getId());

    userService.updateUser(updatedUser);

    session("displayName", user.getName());
    if(user.isManager()) {
      session("manager","manager");
    }

    return ok();
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
