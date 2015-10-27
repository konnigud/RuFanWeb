package is.rufan.user.service;

import is.rufan.user.data.UserNotFoundException;
import is.rufan.user.domain.User;

public interface UserService
{
  public int addUser(User user);
  public User getUser(int id);
  public User getUserByUsername(String username);
  public User updateUser(User user);
}
