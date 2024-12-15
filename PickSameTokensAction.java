public class PickSameTokensAction implements Action
{
    
    //Déclaration des attributs
    private Resource res;
    
    public PickSameTokensAction(Resource r)
    {
        //Constructeur de la classe PickSameTokensAction
        res = r;
    }

    
    public void process(Player player, Board board)
    {
        //Méthode permettant de récupérer 2 même tokens
        if (board.getNbResource(res)>3) {
            
            //Update des informations du plateau et du joueur
            player.updateNbResource(res,2);
            board.updateNbResource(res,-2);
        }
    }
}
