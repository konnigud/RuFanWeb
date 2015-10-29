package is.rufan.fantasy.data;

import is.rufan.fantasy.domain.FantasyTeam;
import is.rufan.fantasy.domain.FantasyTeamPlayer;
import is.rufan.fantasy.domain.FantasyTeamPlayerDetails;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Konni on 27.10.2015.
 */
public class FantasyData extends RuData implements FantasyDataGateway {

    public int createFantasyTeam(FantasyTeam team) {

        SimpleJdbcInsert insertFantasyTeam =
                new SimpleJdbcInsert(getDataSource())
                        .withTableName("fantasyteams")
                        .usingGeneratedKeyColumns("id");

        Map<String,Object> param = new HashMap<String, Object>(3);
        param.put("name",team.getName());
        param.put("username",team.getUserName());
        param.put("tournamentid",team.getTournamentId());

        int returnKey = 0;
        try{
            returnKey = insertFantasyTeam.executeAndReturnKey(param).intValue();

        }catch(DataIntegrityViolationException divex){
            String msg = "Username or tournament id not valid";
            log.warning(msg);
            throw new FantasyTeamNotCreatedException(msg);
        }
        return returnKey;
    }

    public FantasyTeam getById(int id) {
        String sql = "select * from fantasyteams where id = ?";
        JdbcTemplate query = new JdbcTemplate(getDataSource());

        try{
            FantasyTeam team = query.queryForObject(sql,new Object[]{ id },
                new FantasyTeamRowMapper());
            return team;
        }catch(EmptyResultDataAccessException erdax){
            String msg = "Fantasy team "+id+" not found";
            log.warning(msg);
            throw new FantasyTeamNotFoundException(msg);
        }
    }

    public List<FantasyTeam> getAllInTournament(int tournamentId) {
        String sql = "select * from fantasyteams order by tournamentid";
        JdbcTemplate query = new JdbcTemplate(getDataSource());

        List<FantasyTeam> team = query.query(sql,new FantasyTeamRowMapper());
        return team;
    }

    public List<FantasyTeam> getUserFantasyTeams(String username) {
        String sql = "select * from fantasyteams where username = ?";
        JdbcTemplate query = new JdbcTemplate(getDataSource());

        try {
            List<FantasyTeam> team = query.query(sql, new Object[]{username},new FantasyTeamRowMapper());
            return team;
        }catch(EmptyResultDataAccessException erdax){
            String msg = "Fantasy team for "+username+" not found";
            log.warning(msg);
            throw new FantasyTeamNotFoundException(msg);
        }
    }

    public List<FantasyTeam> getUserTeamsInTournament(int tid, String username) {

        String sql = "select * from fantasyteams where username = ? and tournamentid = ?";
        JdbcTemplate query = new JdbcTemplate(getDataSource());

        try {
            List<FantasyTeam> team = query.query(sql, new Object[]{username,tid},new FantasyTeamRowMapper());
            return team;
        }catch(EmptyResultDataAccessException erdax){
            String msg = "Fantasy team for "+username+" not found";
            log.warning(msg);
            throw new FantasyTeamNotFoundException(msg);
        }
    }

    public void insertFantasyPlayers(final List<FantasyTeamPlayer> players){
        String sql = "insert into ftplayers (teamid,playerid,position) values (?,?,?)";

        JdbcTemplate query = new JdbcTemplate(getDataSource());

        try {
            query.batchUpdate(sql, new BatchPreparedStatementSetter() {
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    FantasyTeamPlayer t = players.get(i);
                    ps.setInt(1, t.getTeamid());
                    ps.setInt(2, t.getPlayerid());
                    ps.setString(3,t.getPosistion());
                }

                public int getBatchSize() {
                    return players.size();
                }
            });
        }catch(Exception ex){
            log.warning(ex.getMessage());
        }

    }

    public List<FantasyTeamPlayerDetails> getFantasyTeamPlayers(int teamid){
        String sql = "select " +
                "p.playerid,p.firstname,p.lastname, " +
                "t.abbreviation as team, " +
                "c.abbreviation as country, " +
                "fp.position as position " +
                "from players p " +
                "  join teams t on p.teamid = t.teamid " +
                "  join countries c on p.countryid = c.countryid " +
                "  join ftPlayers fp on p.playerid = fp.playerid " +
                "where fp.teamid = ?";

        JdbcTemplate query = new JdbcTemplate(getDataSource());

        try {
            List<FantasyTeamPlayerDetails> p = query.query(sql, new Object[]{teamid},new FantasyTeamPlayerDetailRowMapper());
            return p;
        }catch(EmptyResultDataAccessException erdax){
            String msg = "Fantasy team "+teamid+" not found";
            log.warning(msg);
            throw new FantasyTeamNotFoundException(msg);
        }catch(Exception ex){
            log.warning(ex.getLocalizedMessage());
            return null;
        }

    }
}
