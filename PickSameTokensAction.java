 


/**
 * Décrivez votre classe PickSameTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickSameTokensAction implements Action
{
    private Resource res;
    /**
     * Constructeur d'objets de classe PickSameTokensAction
     */
    public PickSameTokensAction(Resource r)
    {
        res = r;
    }

    /**
     * Un exemple de méthode - remplacez ce commentaire par le vôtre
     *
     * @param  y   le paramètre de la méthode
     * @return     la somme de x et de y
     */
    public void process(Player player, Board board)
    {
        if (board.getNbResource(res)>3) {
            player.updateNbResource(res,2);
            board.updateNbResource (res,-2);
        }
    }
}
