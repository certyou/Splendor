
/**
 * Décrivez votre classe DiscardTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class DiscardTokensAction implements Action
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre

    /**
     * Constructeur d'objets de classe DiscardTokensAction
     */
    public DiscardTokensAction()
    {
        
    }

    /**
     * Un exemple de méthode - remplacez ce commentaire par le vôtre
     *
     * @param  y   le paramètre de la méthode
     * @return     la somme de x et de y
     */
    public void process(Player player, Ressource choix, Board board)
    {
        if (board.getNbResource(Resource.choix)>3) {
            player.updateNbResource(Resource.choix,2);
        }
    }
}
