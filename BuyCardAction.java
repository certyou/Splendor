public class BuyCardAction implements Action
{
    //Déclaration des attributs
    private DevCard card;

    /**
     * Constructeur de la classe BuyCardAction.
     * Initialise un attribut contenant la carte à acheter
     *
     * parametre: card,    carte à acheter
     */
    public BuyCardAction(DevCard card)
    {
        this.card = card;
    }

    /**
     * Méthode permettant d'acheter une carte
     * 
     * Paramètres : Player, joueur jouant son tour
     *              board, plateau de jeu actuel
     */
    public void process(Player player, Board board)
    {
        
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
