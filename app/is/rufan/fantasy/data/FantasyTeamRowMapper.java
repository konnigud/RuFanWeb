package is.rufan.fantasy.data;

import is.rufan.fantasy.domain.FantasyTeam;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Konni on 27.10.2015.
 */
public class FantasyTeamRowMapper implements RowMapper<FantasyTeam> {
    public FantasyTeam mapRow(ResultSet rs, int i) throws SQLException {
        FantasyTeam team = new FantasyTeam();
        team.setId(rs.getInt("id"));
        team.setName(rs.getString("name"));
        team.setTournamentId(rs.getInt("tournamentid"));
        team.setUserName(rs.getString("username"));

        return team;
    }
}
