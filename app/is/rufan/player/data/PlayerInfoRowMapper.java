package is.rufan.player.data;

import is.rufan.player.domain.PlayerInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Konni on 28.10.2015.
 */
public class PlayerInfoRowMapper implements RowMapper<PlayerInfo> {

    public PlayerInfo mapRow(ResultSet rs, int i) throws SQLException {
        PlayerInfo p = new PlayerInfo();
        p.setBirthdate(rs.getDate("birthdate"));
        p.setCountry(rs.getString("countryname"));
        p.setCountryShort(rs.getString("country"));
        p.setFirstName(rs.getString("firstname"));
        p.setLastName(rs.getString("lastname"));
        p.setHeight(rs.getInt("height"));
        p.setPlayerid(rs.getLong("playerid"));
        p.setPosid(rs.getInt("positionid"));
        p.setPosName(rs.getString("positionname"));
        p.setTeamShort(rs.getString("team"));
        p.setTeam(rs.getString("countryname"));
        p.setWeight(rs.getInt("weight"));
        return p;
    }
}
