public class DiscardTokensAction implements Action
{
    //Déclaration des attributs
    private int nb_res;

    
    public DiscardTokensAction(int nb_res)
    {
        //Constructeur de la classe DiscardTokensAction
        this.nb_res = nb_res;
    }


    public void process(Player player, Board board) throws IllegalArgumentException
    {
        //Méthode permettant d'enlever les tokens en trop
        
        //Récupéaration du choix du joueur
        Resources res = player.chooseDiscardingTokens(nb_res);
        for (Resource resource : res.getAvailableResources()) {
            
            //Update des informations du plateau et du joueur
            player.updateNbResource(resource, -res.getNbResource(resource));
            board.updateNbResource(resource, res.getNbResource(resource));
        }
    }
}
