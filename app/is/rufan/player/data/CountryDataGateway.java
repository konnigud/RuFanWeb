package is.rufan.player.data;

import is.rufan.player.domain.Country;

public interface CountryDataGateway
{
  public void addCountry(Country country);
  public Country getCountry(int countryId);
}
