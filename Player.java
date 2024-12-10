import java.util.ArrayList;


public abstract class Player implements Displayable {
    /* --- Declaration des atribus --- */
    private int id;
    private String name;
    private int points;
    private ArrayList<DevCard> purchasedCards;
    private Resources resources;
    
    public Player(int id, String name){
        this.id = id;
        this.name = name;
        resources = new Resources(0,0,0,0,0);
    }
    
    /* --- Accesseurs des atribus --- */
    public String getName(){return name;}
    public int getPoints(){return points;}
    
    /* --- Accesseurs généraux --- */
    public int getNbTokens(){
        return resources.getNbResource(Resource.DIAMOND) +
               resources.getNbResource(Resource.SAPPHIRE) +
               resources.getNbResource(Resource.EMERALD) + 
               resources.getNbResource(Resource.ONYX) +
               resources.getNbResource(Resource.RUBY);
    }
    
    public int getNbPurchasedCards(){
        return purchasedCards.size();
    }
    
    public int getNbResource(Resource resource){
        int counter = 0;
        counter += resources.getNbResource(resource);
        return counter;
    }
    
    public ArrayList<Resource> getAvailableResources(){
        ArrayList<Resource> availableResources = resources.getAvailableResources();
        return availableResources; 
    }
    
    public int getResFromCards(Resource resource){
        int counter = 0;
        for(DevCard card: purchasedCards){
            if(card.getResourceType().equals(resource)){
                counter ++;
            }
        }
        return counter;
    }
    
    
    /* --- Mutateurs généraux --- */
    public void updateNbResource(Resource resource, int v){
        resources.updateNbResource(resource, v);
    }
    
    public void updatePoints(int p){
        points += p; 
    }
    
    public void addPurchasedCard(DevCard card){
        purchasedCards.add(card);
    }
    
    public boolean canBuyCard(DevCard card){
        boolean verif = true;
        Resources cardCoast = card.getCost();
        int cardRes;
        int playerRes;
        for(Resource resource: cardCoast.getAvailableResources()){
            cardRes = cardCoast.getNbResource(resource);
            playerRes = getNbResource(resource) + getResFromCards(resource);
            if(playerRes < cardRes){
                return false;
            }
        }
        return true;
    }
    
    abstract Action chooseAction() throws IllegalArgumentException;
    abstract Resources chooseDiscardingTokens(int nbTokenToDiscard) throws IllegalArgumentException;
    
    
    
    
    /* --- Stringers --- */
   
     
    public String[] toStringArray(){
        /** EXAMPLE. The number of resource tokens is shown in brackets (), and the number of cards purchased from that resource in square brackets [].
         * Player 1: Camille
         * ⓪pts
         * 
         * ♥R (0) [0]
         * ●O (0) [0]
         * ♣E (0) [0]
         * ♠S (0) [0]
         * ♦D (0) [0]
         */
        String pointStr = " ";
        String[] strPlayer = new String[8];
         
        if(points>0){
            pointStr = new String(new int[] {getPoints()+9311}, 0, 1);
        }else{
            pointStr = "\u24EA";
        }

        
        strPlayer[0] = "Player "+(id+1)+": "+name;
        strPlayer[1] = pointStr + "pts";
        strPlayer[2] = "";
        for(Resource res: resources.getAvailableResources()){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            strPlayer[3+(Resource.values().length-1-res.ordinal())] = res.toSymbol() + " ("+resources.getNbResource(res)+") ["+getResFromCards(res)+"]";
        }
        
        return strPlayer;
    }
}
