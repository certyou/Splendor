public class BuyCardAction implements Action
{
    //Déclaration des attributs
    private DevCard card;

    public BuyCardAction(DevCard card)
    {
        //Constructeur de la classe BuyCardAction
        this.card = card;
    }

    public void process(Player player, Board board)
    {
        // Méthode permettant d'acheter une carte
        
        for (Resource res : card.getCost().getAvailableResources()) {
            
            //Update des informations du plateau et du joueur
            int nouv = card.getCost().getNbResource(res) - player.getResFromCards(res);
            if ( nouv <= 0 ) {
                nouv = 0;
            }
            player.updateNbResource(res, -nouv);
            board.updateNbResource(res, nouv);
        }
        
        //Update du plateau
        board.updateCard(card);
        player.addPurchasedCard(card);
        
        //Ajouts des points au joueur
        player.updatePoints(card.getPoints());
    }
}
