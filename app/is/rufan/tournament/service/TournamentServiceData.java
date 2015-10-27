package is.rufan.tournament.service;

import is.rufan.tournament.data.TournamentDataGateway;
import is.rufan.tournament.data.TournamentNotFoundException;
import is.rufan.tournament.domain.Status;
import is.rufan.tournament.domain.Tournament;
import is.rufan.user.data.UserDataGateway;
import is.ruframework.data.RuDataAccessFactory;
import is.ruframework.domain.RuException;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Konni on 27.10.2015.
 */
public class TournamentServiceData implements TournamentService{

    Logger logger = Logger.getLogger(this.getClass().getName());
    private TournamentDataGateway tournamentDataGateway;
    RuDataAccessFactory factory;

    public TournamentServiceData() throws RuException
    {
        factory = RuDataAccessFactory.getInstance("tournamentdata.xml");
        tournamentDataGateway = (TournamentDataGateway) factory.getDataAccess("tournamentData");
    }

    public int addTournament(Tournament tournament) {
        return tournamentDataGateway.create(tournament);
    }

    public Tournament getTournament(int id) {
        return tournamentDataGateway.getTournamentById(id);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentDataGateway.getAllTournaments();
    }

    public List<Tournament> getAllActiveTournaments() {
        return tournamentDataGateway.getAllActiveTournaments();
    }

    public List<Tournament> getAllOpenTournaments() {
        return tournamentDataGateway.getAllOpenTournaments();
    }

    public List<Tournament> getAllTournamentByWinner(int winnerId) {
        return tournamentDataGateway.getAllTournamentByWinner(winnerId);
    }

    public int updateTournament(Tournament updatedTournament) {
        return tournamentDataGateway
                .updateTournament(updatedTournament);
    }

    public int updateStatus(int id) {

        try {
            Tournament tour = tournamentDataGateway.getTournamentById(id);

            int status = tour.getStatus().getValue();
            if (status == 3) {
                throw new StatusNotUpdateableException();
            }
            status++;

            tour.setStatus(Status.fromInt(status));

            return tournamentDataGateway.updateTournament(tour);

        }catch(TournamentNotFoundException tnfex){
            return -1;
        }
    }
}
