
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
        board.updateCard(card);
        player.addPurchasedCard(card);
        player.updatePoints(card.getPoints());
    }
}
