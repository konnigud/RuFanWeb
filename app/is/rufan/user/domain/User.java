package is.rufan.user.domain;

public class User
{
  protected int id;
  protected String name;
  protected String username;
  protected String email;
  protected String password;
  protected boolean manager;
  protected String favteam;
  protected String creditcard;

  public User()
  {
  }

  public User(int id, String name, String username, String email, String password, boolean manager)
  {
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.manager = manager;
  }

  public User(String name, String username, String email, String password,boolean manager)
  {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.manager = manager;
  }

  public User(int id, String name, String username, String email, String password, boolean manager, String favteam, String creditcard) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
    this.manager = manager;
    this.favteam = favteam;
    this.creditcard = creditcard;
  }

  public int getId()
  {
    return id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getUsername()
  {
    return username;
  }

  public void setUsername(String username)
  {
    this.username = username;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public boolean isManager() {
    return manager;
  }

  public void setManager(boolean manager) {
    this.manager = manager;
  }

  public String getFavteam() {
    return favteam;
  }

  public void setFavteam(String favteam) {
    this.favteam = favteam;
  }

  public String getCreditcard() {
    return creditcard;
  }

  public void setCreditcard(String creditcard) {
    this.creditcard = creditcard;
  }

  @Override
  public String toString()
  {
    return "username: " + username;
  }
}
