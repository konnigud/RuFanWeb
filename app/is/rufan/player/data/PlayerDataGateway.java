package is.rufan.player.data;

import is.rufan.player.domain.Player;

public interface PlayerDataGateway
{
  public void addPlayer(Player player);
  public Player getPlayer(int playerid);
}
