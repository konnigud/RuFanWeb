package is.rufan.player.data;

import is.rufan.player.domain.Position;

import java.util.Collection;

public interface PositionDataGateway
{
  public Position getPosition(int positionId);
  public Collection<Position> getPositions();
}
