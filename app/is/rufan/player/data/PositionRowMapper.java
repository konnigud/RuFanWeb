package is.rufan.player.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import is.rufan.player.domain.Position;
import org.springframework.jdbc.core.RowMapper;

public class PositionRowMapper implements RowMapper<Position>
{
  public Position mapRow(ResultSet rs, int rowNum) throws SQLException
  {
    Position position = new Position();
    position.setPositionId(rs.getInt("positionid"));
    position.setName(rs.getString("name"));
    position.setAbbreviation(rs.getString("abbreviation"));
    return position;
  }
}