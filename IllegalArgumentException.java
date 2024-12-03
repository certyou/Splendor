package Splendor;


/**
 * Décrivez votre classe IllegalArgumentException ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class IllegalArgumentException extends Exception 
{
    /**
     * Constructeur d'objets de classe IllegalArgumentException
     */
    public IllegalArgumentException()
    {
        super("Le nombre de joueur doit être compris entre 2 et 4");
    
    }
    
    /**
     * Constructeur d'objets de classe IllegalArgumentException
     */
    public IllegalArgumentException(String msg)
    {
        super(msg);
    
    }

    
}
