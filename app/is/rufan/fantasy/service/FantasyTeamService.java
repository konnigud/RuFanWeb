package is.rufan.fantasy.service;

import is.rufan.fantasy.domain.FantasyTeam;
import is.rufan.fantasy.domain.FantasyTeamPlayer;
import is.rufan.fantasy.domain.FantasyTeamPlayerDetails;

import java.util.List;

/**
 * Created by Konni on 27.10.2015.
 */
public interface FantasyTeamService {
    public int addFantasyTeam(FantasyTeam team);
    public FantasyTeam getFantasyTeam(int id);
    public List<FantasyTeam> getFantasyTeamsInTournament(int tid);
    public List<FantasyTeam> getUserFantasyTeam(String username);
    public FantasyTeam GetUserFantasyTeamInTournament(String username, int tid);
    public void AddFantasyTeamPlayers(List<FantasyTeamPlayer> players);
    public List<FantasyTeamPlayerDetails> getFantasyTeamPlayers(int teamid);
}
