package is.rufan.user.data;

public class UserDuplicateException extends RuntimeException
{
  public UserDuplicateException()
  {
    super();
  }

  public UserDuplicateException(String message)
  {
    super(message);
  }

  public UserDuplicateException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
