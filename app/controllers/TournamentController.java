package controllers;

import is.rufan.tournament.domain.Tournament;
import is.rufan.tournament.service.TournamentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.mvc.*;
import static play.libs.Json.toJson;
import java.util.List;

import views.html.tournament;

/**
 * Created by Konni on 27.10.2015.
 */
public class TournamentController extends Controller {

    protected ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/tournamentapp.xml");

    public Result getActiveTournaments(){
        TournamentService service = (TournamentService)ctx.getBean("tournamentService");
        List<Tournament> tournaments = service.getAllActiveTournaments();
        return ok(toJson(tournaments));
    }

    public Result blank()
    {
        return ok(tournament.render());
    }
}
