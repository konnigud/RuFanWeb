package is.rufan.player.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import is.rufan.player.domain.Player;
import org.springframework.jdbc.core.RowMapper;

public class PlayerRowMapper implements RowMapper<Player>
{
  public Player mapRow(ResultSet rs, int rowNum) throws SQLException
  {
    Player player = new Player();
    player.setPlayerId(rs.getInt("id"));
    player.setFirstName(rs.getString("firstname"));
    player.setLastName(rs.getString("lastname"));
    player.setHeight(rs.getInt("height"));
    player.setWeight(rs.getInt("weight"));
    player.setBirthDate(rs.getDate("birthday"));
    player.setTeamId(rs.getInt("teamid"));
    return player;
  }
}