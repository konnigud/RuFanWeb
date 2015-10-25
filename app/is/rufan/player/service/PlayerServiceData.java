package is.rufan.player.service;

import is.rufan.player.data.CountryDataGateway;
import is.rufan.player.data.PlayerDataGateway;
import is.rufan.player.data.PositionDataGateway;
import is.rufan.player.domain.Country;
import is.rufan.player.domain.Player;
import is.rufan.player.domain.Position;
import is.ruframework.data.RuDataAccessFactory;
import is.ruframework.domain.RuException;

import java.util.Collection;
import java.util.List;

public class PlayerServiceData implements PlayerService
{
  RuDataAccessFactory factory;
  PlayerDataGateway playerDataGateway;
  PositionDataGateway positionDataGateway;
  CountryDataGateway countryDataGateway;

  public PlayerServiceData() throws RuException
  {
    factory = RuDataAccessFactory.getInstance("playerdata.xml");
    playerDataGateway = (PlayerDataGateway) factory.getDataAccess("playerData");
    positionDataGateway = (PositionDataGateway) factory.getDataAccess("positionData");
    countryDataGateway = (CountryDataGateway) factory.getDataAccess("countryData");
  }

  public Player getPlayer(int playerId)
  {
    return playerDataGateway.getPlayer(playerId);
  }

  public List<Player> getPlayers(int teamId)
  {
    return null;
  }

  public List<Player> getPlayersByTeamAbbreviation(int leagueId, String teamAbbreviation)
  {
    return null;
  }

  public void addPlayer(Player player) throws PlayerServiceException
  {
    playerDataGateway.addPlayer(player);

    Country country = countryDataGateway.getCountry(player.getNationality().getCountryId());
    if(country == null)
      countryDataGateway.addCountry(player.getNationality());
  }

  public Collection<Position> getPositions()
  {
    return positionDataGateway.getPositions();
  }

  public Position getPosition(int positionId)
  {
    return positionDataGateway.getPosition(positionId);
  }
}



