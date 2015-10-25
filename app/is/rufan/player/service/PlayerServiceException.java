package is.rufan.player.service;

public class PlayerServiceException extends RuntimeException
{
  public PlayerServiceException()
  {
  }

  public PlayerServiceException(String message)
  {
    super(message);
  }

  public PlayerServiceException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
