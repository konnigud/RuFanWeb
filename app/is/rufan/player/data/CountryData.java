package is.rufan.player.data;

import is.rufan.player.domain.Country;
import is.ruframework.data.RuData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.Map;

public class CountryData extends RuData implements CountryDataGateway
{
  public void addCountry(Country country)
  {
    SimpleJdbcInsert insertContent =
        new SimpleJdbcInsert(getDataSource())
            .withTableName("countries");

    Map<String, Object> parameters = new HashMap<String, Object>(3);
    parameters.put("countryid", country.getCountryId());
    parameters.put("name", country.getName());
    parameters.put("abbreviation", country.getAbbreviation());

    try
    {
      insertContent.execute(parameters);
    }
    catch (DataIntegrityViolationException divex)
    {
      log.warning("Duplicate entry");
    }

  }

  public Country getCountry(int countryId)
  {
    String sql = "select * from countries where countryid = ?";

    try
    {
      JdbcTemplate queryCountry = new JdbcTemplate(getDataSource());
      Country country = queryCountry.queryForObject(sql, new Object[]{countryId},
          new CountryRowMapper());
      return country;
    }
    catch (EmptyResultDataAccessException e)
    {
      return null;
    }

  }
}

