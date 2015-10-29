package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import is.rufan.fantasy.domain.FantasyTeam;
import is.rufan.fantasy.domain.FantasyTeamPlayer;
import is.rufan.fantasy.domain.FantasyTeamPlayerDetails;
import is.rufan.fantasy.service.FantasyTeamService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;

import static play.libs.Json.toJson;
import views.html.fantasyteam;
import views.html.createteam;
import views.html.fantasyteamdetail;

/**
 * Created by Konni on 27.10.2015.
 */
public class FantasyController  extends Controller{

    protected ApplicationContext ctx =
            new FileSystemXmlApplicationContext("/conf/app.xml");


    public Result getMyFantasyTeam(){
        FantasyTeamService service = (FantasyTeamService)ctx.getBean("fantasyTeamService");
        String username = session().get("username");
        List<FantasyTeam> team = service.getUserFantasyTeam(username);

        return ok(toJson(team));
    }

    public Result blank() {return ok(fantasyteam.render());}

    public Result createblank(String tid) {return ok(createteam.render());}

    //@BodyParser.Of(BodyParser.Json.class)
    public Result createTeam(){
        JsonNode json = request().body().asJson();

        FantasyTeam ft = new FantasyTeam();
        ft.setName(json.findPath("name").asText());
        ft.setTournamentId(json.findPath("tournamentId").asInt());
        ft.setUserName(session().get("username"));
        FantasyTeamService service = (FantasyTeamService)ctx.getBean("fantasyTeamService");
        int teamid = service.addFantasyTeam(ft);

        List<FantasyTeamPlayer> ftP = new ArrayList<FantasyTeamPlayer>();

        for (JsonNode node : json.path("players")){
            ftP.add(new FantasyTeamPlayer(teamid,node.findPath("pid").asInt(),node.findPath("pos").asText()));
        }

        service.AddFantasyTeamPlayers(ftP);

        return ok("ok");
    }

    public Result blankdetails(int id) {
        return ok(fantasyteamdetail.render());
    }

    public Result getFantasyTeamDetails(int id){

        FantasyTeamService service = (FantasyTeamService)ctx.getBean("fantasyTeamService");
        List<FantasyTeamPlayerDetails> p = service.getFantasyTeamPlayers(id);

        return ok(toJson(p));
    }

    public Result getFantasyTeam(int id){

        FantasyTeamService service = (FantasyTeamService)ctx.getBean("fantasyTeamService");
        FantasyTeam t = service.getFantasyTeam(id);

        return ok(toJson(t));
    }
}
