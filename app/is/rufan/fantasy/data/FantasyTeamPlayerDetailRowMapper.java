package is.rufan.fantasy.data;

import is.rufan.fantasy.domain.FantasyTeamPlayerDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Konni on 29.10.2015.
 */
public class FantasyTeamPlayerDetailRowMapper implements RowMapper<FantasyTeamPlayerDetails> {
    public FantasyTeamPlayerDetails mapRow(ResultSet rs, int i) throws SQLException {
        FantasyTeamPlayerDetails p = new FantasyTeamPlayerDetails();
        p.setId(rs.getInt("playerid"));
        p.setFirstName(rs.getString("firstname"));
        p.setLastName(rs.getString("lastname"));
        p.setTeam(rs.getString("team"));
        p.setCountry(rs.getString("country"));
        p.setPosition(rs.getString("position"));
        return p;
    }
}
