package is.rufan.player.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import is.rufan.player.domain.Country;
import org.springframework.jdbc.core.RowMapper;

public class CountryRowMapper implements RowMapper<Country>
{
  public Country mapRow(ResultSet rs, int rowNum) throws SQLException
  {
    Country country = new Country();
    country.setCountryId(rs.getInt("countryid"));
    country.setName(rs.getString("name"));
    country.setAbbreviation(rs.getString("abbreviation"));

    return country;
  }
}