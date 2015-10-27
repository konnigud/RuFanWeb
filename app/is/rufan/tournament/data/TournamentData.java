package is.rufan.tournament.data;

import is.rufan.tournament.domain.Tournament;
import is.ruframework.data.RuData;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Konni on 27.10.2015.
 */
public class TournamentData extends RuData implements TournamentDataGateway{
    public int create(Tournament tournament) {

        SimpleJdbcInsert insertTournament =
                new SimpleJdbcInsert(getDataSource())
                        .withTableName("tournaments")
                        .usingGeneratedKeyColumns("id");

        Map<String,Object> param = new HashMap<String, Object>(6);
        param.put("name",tournament.getName());
        param.put("startdate",tournament.getStartDate());
        param.put("enddate",tournament.getStartDate());
        param.put("status",tournament.getStatus().getValue());
        param.put("leaguename",tournament.getName());
        param.put("winnerid",tournament.getWinnerId());
        param.put("winnername",tournament.getWinnerName());


        int returnKey = 0;
        try
        {
            returnKey = insertTournament.executeAndReturnKey(param).intValue();
        }catch(Exception ex){
            String msg = "Error inserting tournament";
            log.warning(msg+"/r"+ex.getStackTrace());
            throw new TournamentNotInsertedException(msg);
        }
        return returnKey;
    }

    public List<Tournament> getAllTournaments() {
        String sql = "select * from tournaments order by startdate desc";
        JdbcTemplate query = new JdbcTemplate(getDataSource());

        List<Tournament> tournaments = query.query(sql,new TournamentRowMapper());
        return tournaments;
    }

    public List<Tournament> getAllActiveTournaments() {
        String sql = "select * from tournaments where status = 1 order by startdate desc";
        JdbcTemplate query = new JdbcTemplate(getDataSource());

        try {
        List<Tournament> tournaments = query.query(sql,new TournamentRowMapper());
        return tournaments;
        }catch(EmptyResultDataAccessException erdax) {
            String msg = "No active tournaments found";
            log.warning(msg);
            throw new TournamentNotFoundException(msg);
        }
    }

    public List<Tournament> getAllOpenTournaments() {
        String sql = "select * from tournaments where status = 2 order by startdate desc";
        JdbcTemplate query = new JdbcTemplate(getDataSource());

        try {
            List<Tournament> tournaments = query.query(sql, new TournamentRowMapper());
            return tournaments;
        }catch(EmptyResultDataAccessException erdax) {
            String msg = "No open tournaments found";
            log.warning(msg);
            throw new TournamentNotFoundException(msg);
        }
    }

    public Tournament getTournamentById(int i) {
        String sql = "select * from tournaments where id = ?";
        JdbcTemplate query = new JdbcTemplate(getDataSource());

        try {
            Tournament tournament = query.queryForObject(sql, new Object[]{i}, new TournamentRowMapper());
            return tournament;
        }catch(EmptyResultDataAccessException erdax){
            String msg = "Tournament "+i+" not found ";
            log.warning(msg);
            throw new TournamentNotFoundException(msg);
        }

    }

    public List<Tournament> getAllTournamentByWinner(int winnerId) {
        String sql = "select * from tournaments where winnerid = ? order by startdate desc";
        JdbcTemplate query = new JdbcTemplate(getDataSource());

        try {
            List<Tournament> tournaments = query.query(sql, new Object[]{winnerId}, new TournamentRowMapper());
            return tournaments;
        }catch (EmptyResultDataAccessException erdax){
            String msg = "User "+winnerId+" never won";
            log.warning(msg);
            throw new TournamentNotFoundException(msg);
        }
    }

    public int updateTournament(Tournament updatedTournament) {
        String sql = "update tournaments set name = ?, status = ?, startdate = ?, winnername = ?, winnerid = ? where id = ?";

        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());

        Object[] params = { updatedTournament.getName(),
                updatedTournament.getStatus(),
                updatedTournament.getStartDate(),
                updatedTournament.getWinnerName(),
                updatedTournament.getWinnerId(),
                updatedTournament.getId()};

        int[] types = { Types.VARCHAR,
                Types.INTEGER,
                Types.DATE,
                Types.VARCHAR,
                Types.INTEGER,
                Types.INTEGER };
        try{
            jdbcTemplate.update(sql,params,types);
            return updatedTournament.getId();

        }catch(EmptyResultDataAccessException erdax){
            String msg = "Tournament "+updatedTournament.getId()+" not found ";
            log.warning(msg);
            throw new TournamentNotFoundException(msg);
        }
    }
}
