package is.rufan.player.test;

import is.rufan.player.domain.Position;
import is.rufan.player.service.PlayerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Collection;

public class TestPositions
{
  public TestPositions()
  {
    ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:playerapp.xml");
    PlayerService playerService = (PlayerService) applicationContext.getBean("playerService");

    Collection<Position> positions = playerService.getPositions();
    for (Position position : positions)
    {
      System.out.print(position.getName());
      System.out.println(position.getAbbreviation());
    }

    Position p = playerService.getPosition(1);
    System.out.println(p.getAbbreviation());
  }

  public static void main(String[] args)
  {
    new TestPositions();
  }

}

