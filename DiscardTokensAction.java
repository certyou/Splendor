/**
 * Décrivez votre classe DiscardTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class DiscardTokensAction implements Action
{
    private int nb_res;

    /**
     * Constructeur d'objets de classe DiscardTokensAction
     */
    
    public DiscardTokensAction(int nb_res)
    {
        this.nb_res = nb_res;
    }

    /**
     * Un exemple de méthode - remplacez ce commentaire par le vôtre
     *
     * @param  y   le paramètre de la méthode
     * @return     la somme de x et de y
     */
    public void process(Player player, Board board) throws IllegalArgumentException
    {
        Resources res = player.chooseDiscardingTokens(nb_res);
        for (Resource resource : res.getAvailableResources()) {
            player.updateNbResource(resource, -res.getNbResource(resource));
            board.updateNbResource(resource, res.getNbResource(resource));
        }
    }
}
