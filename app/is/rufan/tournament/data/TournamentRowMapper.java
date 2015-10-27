package is.rufan.tournament.data;

import is.rufan.tournament.domain.Status;
import is.rufan.tournament.domain.Tournament;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Konni on 27.10.2015.
 */
public class TournamentRowMapper implements RowMapper<Tournament>{

    public Tournament mapRow(ResultSet rs, int rowNum) throws SQLException{

        Tournament tournament = new Tournament();
        tournament.setId(rs.getInt("id"));
        tournament.setName(rs.getString("name"));
        tournament.setStartDate(rs.getDate("startdate"));
        tournament.setStatus(Status.fromInt(rs.getInt("status")));

        tournament.setWinnerId(rs.getInt("winnerid"));
        tournament.setWinnerName(rs.getString("winnername"));
        return tournament;

        //tournament.setStartDate(rs.getDate(rs.getDate("startdate").toString()));
    }
}
