package is.rufan.fantasy.domain;

/**
 * Created by Konni on 27.10.2015.
 */
public class FantasyTeam {
    protected int id;
    protected String username;
    protected int tournamentId;
    protected String name;

    public FantasyTeam() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
