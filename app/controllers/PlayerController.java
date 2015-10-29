package controllers;

import is.rufan.player.domain.PlayerInfo;
import is.rufan.player.service.PlayerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.mvc.*;

import java.util.List;

import static play.libs.Json.toJson;

/**
 * Created by Konni on 28.10.2015.
 */
public class PlayerController extends Controller {

    protected ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/app.xml");



    public Result getAllPlayers(){
        PlayerService service = (PlayerService)ctx.getBean("playerService");
        List<PlayerInfo> p = service.getPlayersInfo();
        return ok(toJson(p));
    }

}
