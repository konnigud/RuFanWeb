package is.rufan.user.data;


public class UserNotFoundException extends RuntimeException
{
  public UserNotFoundException()
  {
    super();
  }

  public UserNotFoundException(String message)
  {
    super(message);
  }

  public UserNotFoundException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
