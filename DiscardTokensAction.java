
/**
 * Décrivez votre classe DiscardTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class DiscardTokensAction implements Action
{
    private Resource res1 = null;
    private Resource res2 = null;
    private Resource res3 = null;

    /**
     * Constructeur d'objets de classe DiscardTokensAction
     */
    
    public DiscardTokensAction(Resource res1)
    {
        this.res1 = res1;
    }
    
    
    public DiscardTokensAction(Resource res1,Resource res2)
    {
        this.res1 = res1;
        this.res2 = res2;
    }
    
    public DiscardTokensAction(Resource res1,Resource res2, Resource res3)
    {
        this.res1 = res1;
        this.res2 = res2;
        this.res3 = res3;
    }

    /**
     * Un exemple de méthode - remplacez ce commentaire par le vôtre
     *
     * @param  y   le paramètre de la méthode
     * @return     la somme de x et de y
     */
    public void process(Player player, Board board)
    {
        player.updateNbResource(res1,-1);
        if (res2 != null) {
            player.updateNbResource(res2,-1);
        }
        if (res3 != null) {
            player.updateNbResource(res3,-1);
        }
    }
}
