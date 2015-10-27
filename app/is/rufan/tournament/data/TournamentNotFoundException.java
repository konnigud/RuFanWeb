package is.rufan.tournament.data;

/**
 * Created by Konni on 27.10.2015.
 */
public class TournamentNotFoundException extends RuntimeException
{
    public TournamentNotFoundException()
    {
        super();
    }

    public TournamentNotFoundException(String message)
    {
        super(message);
    }

    public TournamentNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}