package is.rufan.player.service;

import is.rufan.player.domain.Player;
import is.rufan.player.domain.Position;

import java.util.Collection;
import java.util.List;

public interface PlayerService
{
  Player getPlayer(int playerId);
  List<Player> getPlayers(int teamId);
  List<Player> getPlayersByTeamAbbreviation(int leagueId, String teamAbbreviation);
  void addPlayer(Player player) throws PlayerServiceException;

  Collection<Position> getPositions();
  Position getPosition(int positionId);
}
