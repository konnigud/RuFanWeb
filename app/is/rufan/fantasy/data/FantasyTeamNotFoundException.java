package is.rufan.fantasy.data;

/**
 * Created by Konni on 27.10.2015.
 */
public class FantasyTeamNotFoundException extends RuntimeException
{
    public FantasyTeamNotFoundException()
    {
        super();
    }

    public FantasyTeamNotFoundException(String message)
    {
        super(message);
    }

    public FantasyTeamNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}