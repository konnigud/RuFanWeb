package is.rufan.tournament.data;

import is.rufan.tournament.domain.Tournament;

import java.util.List;

/**
 * Created by Konni on 27.10.2015.
 */
public interface TournamentDataGateway {
    public int create(Tournament tournament);
    public List<Tournament> getAllTournaments();
    public List<Tournament> getAllActiveTournaments();
    public List<Tournament> getAllOpenTournaments();
    public Tournament getTournamentById(int i);
    public List<Tournament> getAllTournamentByWinner(int winnerId);
    public int updateTournament(Tournament updatedTournament);
}
