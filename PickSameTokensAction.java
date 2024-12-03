package Splendor;


/**
 * Décrivez votre classe PickSameTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickSameTokensAction implements Action
{

    /**
     * Constructeur d'objets de classe PickSameTokensAction
     */
    public PickSameTokensAction()
    {
        // initialisation des variables d'instance
        x = 0;
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
