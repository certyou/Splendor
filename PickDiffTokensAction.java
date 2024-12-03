
/**
 * Décrivez votre classe PickDiffTokensAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class PickDiffTokensAction implements Action
{
    private Resources res1;

    /**
     * Constructeur d'objets de classe PickDiffTokensAction
     */
    public PickDiffTokensAction(Resources res1,Resources res2, Resources res3 )
    {
        this.res1 = res1
        this.res2 = res2
        this.res3 = res3
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
