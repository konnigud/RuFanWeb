package is.rufan.tournament.service;

import is.rufan.tournament.domain.Tournament;

import java.util.List;

/**
 * Created by Konni on 27.10.2015.
 */
public interface TournamentService {
    public int addTournament(Tournament tournament);
    public Tournament getTournament(int id);
    public List<Tournament> getAllTournaments();
    public List<Tournament> getAllActiveTournaments();
    public List<Tournament> getAllOpenTournaments();
    public List<Tournament> getAllTournamentByWinner(int winnerId);
    public int updateTournament(Tournament updatedTournament);
    public int updateStatus(int id);
}
