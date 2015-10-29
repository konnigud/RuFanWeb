package is.rufan.fantasy.data;

/**
 * Created by Konni on 27.10.2015.
 */
public class FantasyTeamNotCreatedException extends RuntimeException
{
    public FantasyTeamNotCreatedException()
    {
        super();
    }

    public FantasyTeamNotCreatedException(String message)
    {
        super(message);
    }

    public FantasyTeamNotCreatedException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
