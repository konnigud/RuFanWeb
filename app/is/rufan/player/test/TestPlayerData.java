package is.rufan.player.test;

import is.rufan.player.domain.Country;
import is.rufan.player.domain.Player;
import is.rufan.player.service.PlayerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Date;

public class TestPlayerData
{
  public TestPlayerData()
  {

    ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:app.xml");
    PlayerService playerService = (PlayerService)applicationContext.getBean("playerService");

    System.out.println(playerService.getPlayer(457));

    Player player = new Player();
    player.setFirstName("firstname");
    player.setLastName("lastname");
    player.setHeight(190);
    player.setWeight(90);
    player.setBirthDate(new Date());
    player.setTeamId(1);
    player.setNationality(new Country(1, "Iceland", "IS"));

    playerService.addPlayer(player);
  }

  public static void main(String[] args)
  {
    new TestPlayerData();
  }
}
