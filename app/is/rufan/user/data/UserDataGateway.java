package is.rufan.user.data;

import is.rufan.user.domain.User;
import is.ruframework.data.RuDataAccess;

public interface UserDataGateway extends RuDataAccess
{
  public int create(User user);
  public User find(int userid);
  public User find(String username);
  public User update(User user);
}
