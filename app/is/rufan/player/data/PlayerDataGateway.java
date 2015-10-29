package is.rufan.player.data;

import is.rufan.player.domain.Player;
import is.rufan.player.domain.PlayerInfo;

import java.util.List;

public interface PlayerDataGateway
{
  public void addPlayer(Player player);
  public Player getPlayer(int playerid);
  public List<PlayerInfo> getAllPlayers();
}
