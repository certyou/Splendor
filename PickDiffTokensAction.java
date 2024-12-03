
/**
 * Décrivez votre classe PickDiffTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickDiffTokensAction implements Action
{
    private Resource res1;
    private Resource res2;
    private Resource res3;

    /**
     * Constructeur d'objets de classe PickDiffTokensAction
     */
    public PickDiffTokensAction(Resource res1,Resource res2, Resource res3 )
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
        player.updateNbResource(res1,1);
        player.updateNbResource(res2,1);
        player.updateNbResource(res3,1);
        board.updateNbResource(res1,-1);
        board.updateNbResource(res2,-1);
        board.updateNbResource(res3,-1);
    } 
}
