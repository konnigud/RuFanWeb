package is.rufan.user.service;

import is.rufan.user.data.UserDataGateway;
import is.rufan.user.domain.User;
import is.ruframework.data.RuDataAccessFactory;
import is.ruframework.domain.RuException;

import java.util.logging.Logger;

public class UserServiceData implements UserService
{
  Logger logger = Logger.getLogger(this.getClass().getName());
  private UserDataGateway userDataGateway;
  RuDataAccessFactory factory;

  public UserServiceData() throws RuException
  {
    factory = RuDataAccessFactory.getInstance("userdata.xml");
    userDataGateway = (UserDataGateway) factory.getDataAccess("playerData");
  }

  public int addUser(User user)
  {
    return userDataGateway.create(user);
  }

  public User getUser(int id)
  {
    return userDataGateway.find(id);
  }

  public User getUserByUsername(String username)
  {
    return userDataGateway.find(username);
  }

  public User updateUser(User user) {
    return userDataGateway.update(user);
  }
}
