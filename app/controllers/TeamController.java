package controllers;

import is.rufan.team.domain.Team;
import is.rufan.team.service.TeamService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import play.mvc.Controller;
import play.mvc.*;
import static play.libs.Json.toJson;
import java.util.List;

/**
 * Created by Konni on 26.10.2015.
 */
public class TeamController extends Controller {

    protected ApplicationContext ctx = new FileSystemXmlApplicationContext("/conf/teamapp.xml");

    public Result getTeams(){
        TeamService teamservice = (TeamService)ctx.getBean("teamService");
        List<Team> teams = teamservice.getTeams();
        return ok(toJson(teams));
    }
}
