package is.rufan.player.process;

import is.rufan.player.domain.Player;
import is.rufan.player.service.PlayerService;
import is.rufan.player.service.PlayerServiceException;
import is.ruframework.process.RuAbstractProcess;
import is.ruframework.reader.RuReadHandler;
import is.ruframework.reader.RuReader;
import is.ruframework.reader.RuReaderException;
import is.ruframework.reader.RuReaderFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.logging.Logger;

public class PlayerImportProcess extends RuAbstractProcess implements RuReadHandler
{
  private PlayerService playerService;
  MessageSource msg;
  Logger logger = Logger.getLogger(this.getClass().getName());

  @Override
  public void beforeProcess()
  {
    ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:playerapp.xml");
    playerService = (PlayerService) applicationContext.getBean("playerService");
    msg = (MessageSource) applicationContext.getBean("messageSource");
    logger.info("processbefore: " + getProcessContext().getProcessName());
  }

  @Override
  public void startProcess()
  {
    RuReaderFactory factory = new RuReaderFactory("playerprocess.xml");
    RuReader reader = factory.getReader("playerReader");

    reader.setReadHandler(this);
    try
    {
      reader.read();
    }
    catch (RuReaderException e)
    {
      String errorMsg = "Unable to read specified file.";
      logger.severe(errorMsg);
    }
  }

  public void read(int count, Object object)
  {
    Player player = (Player) object;
    try
    {
      playerService.addPlayer(player);
    }
    catch (PlayerServiceException se)
    {
      logger.warning("Player id " + player.getPlayerId() + " not added " + se.getMessage());
    }
  }
}
