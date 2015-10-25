package is.rufan.player.process;

import is.rufan.player.domain.Country;
import is.rufan.player.domain.Player;
import is.rufan.player.domain.Position;
import is.ruframework.reader.RuAbstractReader;
import is.ruframework.reader.RuJsonUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.*;

public class PlayerReader extends RuAbstractReader
{
  public Object parse(String content)
  {
    // Root object
    JSONObject jsonObject = (JSONObject) JSONValue.parse(content);

    // Get apiResults, this is an array so get the first (and only) item
    JSONArray apiResults = (JSONArray) jsonObject.get("apiResults");
    JSONObject jTmp = (JSONObject) apiResults.get(0);

    // League
    JSONObject jLeague = (JSONObject) jTmp.get("league");

    // Get Players
    JSONArray jPlayers = (JSONArray) jLeague.get("players");
    JSONObject jPlayer;
    List<Player> players = new ArrayList<Player>();
    Player player;
    for (int i = 0; i < jPlayers.size(); i++)
    {
      player = new Player();
      jPlayer = (JSONObject) jPlayers.get(i);
      player.setPlayerId(RuJsonUtil.getInt(jPlayer, "playerId"));
      player.setFirstName((String) jPlayer.get("firstName"));
      player.setLastName((String) jPlayer.get("lastName"));
      jTmp = (JSONObject) jPlayer.get("height");
      player.setHeight(RuJsonUtil.getInt(jTmp, "centimeters"));
      jTmp = (JSONObject) jPlayer.get("weight");
      player.setWeight(RuJsonUtil.getInt(jTmp, "kilograms"));
      jTmp = (JSONObject) jPlayer.get("birth");
      jTmp = (JSONObject) jTmp.get("birthDate");
      player.setBirthDate(RuJsonUtil.newDate(RuJsonUtil.getInt(jTmp, "year"),
          RuJsonUtil.getInt(jTmp, "month"),
          RuJsonUtil.getInt(jTmp, "date")));

      jTmp = (JSONObject) jPlayer.get("team");
      player.setTeamId(RuJsonUtil.getInt(jTmp, "teamId"));

      jTmp = (JSONObject) jPlayer.get("nationality");
      Country country = new Country();
      country.setCountryId(RuJsonUtil.getInt(jTmp, "countryId"));
      country.setName((String) jTmp.get("name"));
      country.setAbbreviation((String) jTmp.get("abbreviation"));
      player.setNationality(country);

      Position position;
      JSONArray jPositions = (JSONArray) jPlayer.get("positions");
      Iterator it = jPositions.iterator();
      while(it.hasNext())
      {
        jTmp = (JSONObject)it.next();
        position = new Position(RuJsonUtil.getInt(jTmp, "positionId"),
            (String)jTmp.get("name"),
            (String)jTmp.get("abbreviation"),
            RuJsonUtil.getInt(jTmp, "sequence"));
        player.addPosition(position);
      }

      players.add(player);
      readHandler.read(i, player);
    }

    return players;
  }
}
