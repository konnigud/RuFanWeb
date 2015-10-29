package is.rufan.fantasy.data;

import is.rufan.fantasy.domain.FantasyTeam;
import is.rufan.fantasy.domain.FantasyTeamPlayer;
import is.rufan.fantasy.domain.FantasyTeamPlayerDetails;

import java.util.List;

/**
 * Created by Konni on 27.10.2015.
 */
public interface FantasyDataGateway {
    public int createFantasyTeam(FantasyTeam team);
    public FantasyTeam getById(int id);
    public List<FantasyTeam> getUserTeamsInTournament(int tid,String username);
    public List<FantasyTeam> getAllInTournament(int tournamentId);
    public List<FantasyTeam> getUserFantasyTeams(String username);
    public void insertFantasyPlayers(List<FantasyTeamPlayer> players);
    public List<FantasyTeamPlayerDetails> getFantasyTeamPlayers(int teamid);
}
