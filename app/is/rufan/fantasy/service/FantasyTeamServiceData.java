package is.rufan.fantasy.service;

import is.rufan.fantasy.data.FantasyDataGateway;
import is.rufan.fantasy.domain.FantasyTeam;
import is.rufan.fantasy.domain.FantasyTeamPlayer;
import is.rufan.fantasy.domain.FantasyTeamPlayerDetails;
import is.ruframework.data.RuDataAccessFactory;
import is.ruframework.domain.RuException;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Konni on 27.10.2015.
 */
public class FantasyTeamServiceData implements FantasyTeamService {

    Logger logger = Logger.getLogger(this.getClass().getName());
    private FantasyDataGateway fantasyDataGateway;
    RuDataAccessFactory factory;

    public FantasyTeamServiceData() throws RuException
    {
        factory = RuDataAccessFactory.getInstance("appdata.xml");
        fantasyDataGateway = (FantasyDataGateway) factory.getDataAccess("fantasyTeamData");
    }

    public int addFantasyTeam(FantasyTeam team) {
        return fantasyDataGateway.createFantasyTeam(team);
    }

    public FantasyTeam getFantasyTeam(int id) {
        return fantasyDataGateway.getById(id);
    }

    public List<FantasyTeam> getFantasyTeamsInTournament(int tid) {
        return fantasyDataGateway.getAllInTournament(tid);
    }

    public List<FantasyTeam> getUserFantasyTeam(String username) {
        return fantasyDataGateway.getUserFantasyTeams(username);
    }

    public FantasyTeam GetUserFantasyTeamInTournament(String username, int tid) {
        List<FantasyTeam> teams = fantasyDataGateway.getUserTeamsInTournament(tid,username);
        return teams.get(0);
    }

    public void AddFantasyTeamPlayers(List<FantasyTeamPlayer> players){
        fantasyDataGateway.insertFantasyPlayers(players);
    }

    public List<FantasyTeamPlayerDetails> getFantasyTeamPlayers(int teamid) {
        return fantasyDataGateway.getFantasyTeamPlayers(teamid);
    }
}
