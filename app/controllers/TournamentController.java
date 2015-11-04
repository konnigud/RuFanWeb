package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import is.rufan.tournament.domain.Tournament;
import is.rufan.tournament.service.TournamentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.mvc.*;
import static play.libs.Json.toJson;
import java.util.List;

import views.html.tournament;
import views.html.tournamentsall;
import views.html.createteam;

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

    public Result getAllTournaments(){
        TournamentService service = (TournamentService)ctx.getBean("tournamentService");
        List<Tournament> tournaments = service.getAllTournaments();
        return ok(toJson(tournaments));
    }


    public Result getTournamentByTextId(int id){

        TournamentService service = (TournamentService)ctx.getBean("tournamentService");
        Tournament t = service.getTournament(id);
        return ok(toJson(t));
    }

    public Result blank()
    {
        return ok(tournament.render());
    }

    public Result allTournamentsBlank() {return ok(tournamentsall.render());}

}
