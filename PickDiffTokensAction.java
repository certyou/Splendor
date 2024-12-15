public class PickDiffTokensAction implements Action
{
    //Déclaration des attributs
    private Resource res1;
    private Resource res2;
    private Resource res3;

    public PickDiffTokensAction(Resource res1,Resource res2, Resource res3 )
    {
        //Constructeur de la classe PickDiffTokensAction
        this.res1 = res1;
        this.res2 = res2;
        this.res3 = res3;
    }

    public void process(Player player, Board board)
    {
        //Méthode permettant de récupérer les 3 tokens
        
        //Update des informations du plateau et du joueur
        player.updateNbResource(res1,1);
        player.updateNbResource(res2,1);
        player.updateNbResource(res3,1);
        board.updateNbResource(res1,-1);
        board.updateNbResource(res2,-1);
        board.updateNbResource(res3,-1);
    } 
}
