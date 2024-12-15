public class PickDiffTokensAction implements Action
{
    //Déclaration des attributs
    private Resource res1;
    private Resource res2;
    private Resource res3;

    /**
     * Constructeur de la classe PickDiffTokensAction.
     * Initialise 3 attributs avec les noms des tokens à récupérer
     *
     * parametre:   res1,    nom du token 1 à récupérer
     *              res2,    nom du token 2 à récupérer
     *              res3,    nom du token 3 à récupérer
     */
    public PickDiffTokensAction(Resource res1,Resource res2, Resource res3 )
    {
        this.res1 = res1;
        this.res2 = res2;
        this.res3 = res3;
    }

    /**
     * Méthode permettant de récupérer les 3 tokens
     * 
     * Paramètres : Player, joueur jouant son tour
     *              board, plateau de jeu actuel
     */
    public void process(Player player, Board board)
    {
        
        //Update des informations du plateau et du joueur
        player.updateNbResource(res1,1);
        player.updateNbResource(res2,1);
        player.updateNbResource(res3,1);
        board.updateNbResource(res1,-1);
        board.updateNbResource(res2,-1);
        board.updateNbResource(res3,-1);
    } 
}
