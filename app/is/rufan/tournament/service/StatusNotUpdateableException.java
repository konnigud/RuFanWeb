package is.rufan.tournament.service;

/**
 * Created by Konni on 27.10.2015.
 */

public class StatusNotUpdateableException extends RuntimeException
{
    public StatusNotUpdateableException()
    {
        super();
    }

    public StatusNotUpdateableException(String message)
    {
        super(message);
    }

    public StatusNotUpdateableException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
