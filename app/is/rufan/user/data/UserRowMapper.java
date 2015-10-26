package is.rufan.user.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import is.rufan.user.domain.User;
import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User>
{

  public User mapRow(ResultSet rs, int rowNum) throws SQLException
  {
    User user = new User();
    user.setId(rs.getInt("id"));
    user.setName(rs.getString("name"));
    user.setUsername(rs.getString("username"));
    user.setEmail(rs.getString("email"));
    user.setPassword(rs.getString("password"));
    user.setManager(rs.getBoolean("manager"));
    user.setFavteam(rs.getString("favteam"));
    user.setCreditcard(rs.getString("creditcard"));
    return user;
  }
}

