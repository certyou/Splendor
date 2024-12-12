
/**
 * Décrivez votre classe BuyCardAction ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class BuyCardAction implements Action
{
    private DevCard card;

    /**
     * Constructeur d'objets de classe BuyCardAction
     */
    public BuyCardAction(DevCard card)
    {
        this.card = card;
    }

    /**
     * Un exemple de méthode - remplacez ce commentaire par le vôtre
     *
     * @param  y   le paramètre de la méthode
     * @return     la somme de x et de y
     */
    public void process(Player player, Board board)
    {
        
        for (Resource res : card.getCost().getAvailableResources()) {
            int nouv = card.getCost().getNbResource(res) - player.getResFromCards(res);
            if ( nouv <= 0 ) {
                nouv = 0;
            }
            player.updateNbResource(res, -nouv);
            board.updateNbResource(res, nouv);
        }
        board.updateCard(card);
        player.addPurchasedCard(card);
        player.updatePoints(card.getPoints());
    }
}
