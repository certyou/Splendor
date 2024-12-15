public class PickSameTokensAction implements Action
{
    
    //Déclaration des attributs
    private Resource res;
    
    /**
     * Constructeur de la classe PickSameTokensAction.
     * Initialise un attribut contenant le nom du token à récupérer
     *
     * parametre: r,    nom du token à récupérer
     */
    public PickSameTokensAction(Resource r)
    {
        res = r;
    }

    /**
     * Méthode permettant de récupérer 2 même tokens
     * 
     * Paramètres : Player, joueur jouant son tour
     *              board, plateau de jeu actuel
     */
    public void process(Player player, Board board)
    {
        if (board.getNbResource(res)>3) {
            
            //Update des informations du plateau et du joueur
            player.updateNbResource(res,2);
            board.updateNbResource(res,-2);
        }
    }
}
