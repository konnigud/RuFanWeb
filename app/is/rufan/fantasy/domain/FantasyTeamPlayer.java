package is.rufan.fantasy.domain;

/**
 * Created by Konni on 28.10.2015.
 */
public class FantasyTeamPlayer {
    protected int teamid;
    protected int playerid;
    protected String posistion;

    public FantasyTeamPlayer() {
    }

    public FantasyTeamPlayer(int teamid, int playerid) {
        this.teamid = teamid;
        this.playerid = playerid;
    }

    public FantasyTeamPlayer(int teamid, int playerid, String posistion) {
        this.teamid = teamid;
        this.playerid = playerid;
        this.posistion = posistion;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public String getPosistion() {
        return posistion;
    }

    public void setPosistion(String posistion) {
        this.posistion = posistion;
    }
}
