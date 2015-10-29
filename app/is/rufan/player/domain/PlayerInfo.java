package is.rufan.player.domain;

import java.util.Date;

/**
 * Created by Konni on 28.10.2015.
 */
public class PlayerInfo {
    protected long playerid;
    protected String firstName;
    protected String lastName;
    protected int height;
    protected int weight;
    protected Date birthdate;
    protected String teamShort;
    protected String team;
    protected String countryShort;
    protected String country;
    protected int posid;
    protected String posName;

    public PlayerInfo() {
    }

    public long getPlayerid() {
        return playerid;
    }

    public void setPlayerid(long playerid) {
        this.playerid = playerid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getTeamShort() {
        return teamShort;
    }

    public void setTeamShort(String teamShort) {
        this.teamShort = teamShort;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCountryShort() {
        return countryShort;
    }

    public void setCountryShort(String countryShort) {
        this.countryShort = countryShort;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPosid() {
        return posid;
    }

    public void setPosid(int posid) {
        this.posid = posid;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }
}
