package is.rufan.player.data;

import is.rufan.player.domain.Position;
import is.ruframework.data.RuData;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collection;

public class PositionData extends RuData implements PositionDataGateway
{
  public Position getPosition(int positionId)
  {
    String sql = "select * from positions where positionid=?";
    JdbcTemplate queryPosition= new JdbcTemplate(getDataSource());

    Position position = queryPosition.queryForObject(sql,
        new Object[] { positionId },
        new PositionRowMapper());

    return position;
  }

  public Collection<Position> getPositions()
  {
    String sql = "select * from positions";
    JdbcTemplate queryPosition= new JdbcTemplate(getDataSource());

    Collection<Position> positions = queryPosition.query(sql,
        new PositionRowMapper());
    return positions;
  }
}

