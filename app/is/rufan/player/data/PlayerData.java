package is.rufan.player.data;

import is.rufan.player.domain.Player;
import is.rufan.player.domain.PlayerInfo;
import is.rufan.player.domain.Position;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerData extends RuData implements PlayerDataGateway
{
  public void addPlayer(Player player)
  {
    SimpleJdbcInsert insertPlayer =
        new SimpleJdbcInsert(getDataSource())
            .withTableName("players");

    Map<String, Object> playerParameters = new HashMap<String, Object>(8);
    playerParameters.put("playerid", player.getPlayerId());
    playerParameters.put("firstname", player.getFirstName());
    playerParameters.put("lastname", player.getLastName());
    playerParameters.put("height", player.getHeight());
    playerParameters.put("weight", player.getWeight());
    playerParameters.put("birthdate", player.getBirthDate());
    playerParameters.put("countryid", player.getNationality().getCountryId());
    playerParameters.put("teamid", player.getTeamId());

    try
    {
      insertPlayer.execute(playerParameters);
    }
    catch (DataIntegrityViolationException divex)
    {
      log.warning("Duplicate entry");
    }

    SimpleJdbcInsert insertPositions =
        new SimpleJdbcInsert(getDataSource())
            .withTableName("playerpositions");

    Collection<Position> positions = player.getPositions();
    for(Position position : positions)
    {
      Map<String, Object> positionParameters = new HashMap<String, Object>(2);
      positionParameters.put("positionid", position.getPositionId());
      positionParameters.put("playerid", player.getPlayerId());
      try
      {
        insertPositions.execute(positionParameters);
      }
      catch (DataIntegrityViolationException divex)
      {
        log.warning("Duplicate entry");
      }
    }
  }

  public Player getPlayer(int playerid)
  {
    String sql = "select * from players where id = ?";
    JdbcTemplate queryPlayer= new JdbcTemplate(getDataSource());
    Player player = queryPlayer.queryForObject(sql, new Object[] { playerid },
        new PlayerRowMapper());
    return player;
  }

  public List<PlayerInfo> getAllPlayers() {
        String sql = "select " +
            "  p.playerid,p.firstname,p.lastname,p.height,p.weight,p.birthdate, " +
            "  t.abbreviation as team,t.displayname as teamname, " +
            "  c.name as countryname,c.abbreviation as country, " +
            "  po.name as positionname, po.positionid " +
            "from players p " +
            "join teams t on p.teamid = t.teamid " +
            "join countries c on p.countryid = c.countryid " +
            "join playerpositions pp on p.playerid = pp.playerid " +
            "join positions po on pp.positionid = po.positionid";

        JdbcTemplate queryPlayer= new JdbcTemplate(getDataSource());

        try {
            List<PlayerInfo> p = queryPlayer.query(sql, new PlayerInfoRowMapper());
            return p;
        }
        catch(Exception ex)
        {
            return null;
        }

  }
}

