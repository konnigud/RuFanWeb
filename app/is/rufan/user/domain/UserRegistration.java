package is.rufan.user.domain;

public class UserRegistration extends User
{
  protected String repeatPassword;

  public String getRepeatPassword()
  {
    return repeatPassword;
  }

  public void setRepeatPassword(String repeatPassword)
  {
    this.repeatPassword = repeatPassword;
  }
}
