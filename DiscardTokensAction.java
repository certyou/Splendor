public class DiscardTokensAction implements Action
{
    //Déclaration des attributs
    private int nb_res;

    /**
     * Constructeur de la classe DiscardTokensAction.
     * Initialise un attribut contenant le nombre de tokens à enlever
     *
     * parametre: nb_res,    nombre de tokens à enlever
     */
    public DiscardTokensAction(int nb_res)
    {
        this.nb_res = nb_res;
    }


    /**
     * Méthode permettant d'enlever les tokens en trop
     * 
     * Paramètres : Player, joueur jouant son tour
     *              board, plateau de jeu actuel
     */
    public void process(Player player, Board board) throws IllegalArgumentException
    {
        
        //Récupéaration du choix du joueur
        Resources res = player.chooseDiscardingTokens(nb_res);
        for (Resource resource : res.getAvailableResources()) {
            
            //Update des informations du plateau et du joueur
            player.updateNbResource(resource, -res.getNbResource(resource));
            board.updateNbResource(resource, res.getNbResource(resource));
        }
    }
}
