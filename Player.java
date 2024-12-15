import java.util.ArrayList;

/**
 * Classe abstraite Player représentant un joueur dans le jeu.
 * Implémente l'interface Displayable.
 */
public abstract class Player implements Displayable {
    /* --- Declaration des atribus --- */
    private int id;
    private String name;
    private int points;
    private ArrayList<DevCard> purchasedCards;
    private Resources resources;
    
    /**
     * Constructeur de la classe Player.
     * Initialise un joueur avec un identifiant unique et un nom.
     * Les ressources et les cartes achetées sont initialisées par défaut.
     *
     * paramètre:: id,   L'identifiant unique du joueur.
     * paramètre:: name, Le nom du joueur.
     */
    public Player(int id, String name){
        purchasedCards = new ArrayList<DevCard>();
        this.id = id;
        this.name = name;
        resources = new Resources(0,0,0,0,0);
        purchasedCards = new ArrayList<>();
    }
    
    /* --- Accesseurs des atribus --- */
    /**
     * Obtient le nom du joueur.
     * return: Le nom du joueur.
     */
    public String getName(){return name;}
    /**
     * Obtient le nombre total de points du joueur.
     * @return Le nombre de points.
     */
    public int getPoints(){return points;}
    /**
     * Obtient l'identifiant unique du joueur.
     * return: L'identifiant du joueur.
     */
    public int getId(){return id;}
    
    /* --- Accesseurs généraux --- */
    /**
     * Calcule le nombre total de jetons possédés par le joueur.
     * return: Le nombre total de jetons.
     */
    public int getNbTokens(){
        return resources.getNbResource(Resource.DIAMOND) +
               resources.getNbResource(Resource.SAPPHIRE) +
               resources.getNbResource(Resource.EMERALD) + 
               resources.getNbResource(Resource.ONYX) +
               resources.getNbResource(Resource.RUBY);
    }
    
    /**
     * Obtient le nombre total de cartes de développement achetées par le joueur.
     * return: Le nombre de cartes achetées.
     */
    public int getNbPurchasedCards(){
        return purchasedCards.size();
    }
    
    /**
     * Retourne le nombre de ressources d'un type donné possédées par le joueur.
     * paramètre: resource, Le type de ressource à vérifier.
     * return: Le nombre de ressources de ce type.
     */
    public int getNbResource(Resource resource){
        int counter = 0;
        counter += resources.getNbResource(resource);
        return counter;
    }
    
    /**
     * Retourne une liste des types de ressources actuellement disponibles
     * pour le joueur.
     * return: Une liste des types de ressources disponibles.
     */
    public ArrayList<Resource> getAvailableResources(){
        ArrayList<Resource> availableResources = resources.getAvailableResources();
        return availableResources; 
    }
    
    /**
     * Calcule le nombre de ressources d'un type donné générées par les cartes
     * achetées par le joueur.
     * paramètre: resource, Le type de ressource à vérifier.
     * return: Le nombre de ressources générées par les cartes.
     */
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
    /**
     * Met à jour le nombre de ressources d'un type donné pour le joueur.
     * paramètre: resource, Le type de ressource à mettre à jour.
     * paramètre: v,        La valeur de la mise à jour (peut être positive ou négative).
     */
    public void updateNbResource(Resource resource, int v){
        resources.updateNbResource(resource, v);
    }
    
    /**
     * Ajoute un nombre de points au score du joueur.
     * paramètre: p, Le nombre de points à ajouter.
     */
    public void updatePoints(int p){
        points += p; 
    }
    
    /**
     * Ajoute une carte de développement à la liste des cartes achetées
     * par le joueur.
     * paramètre: card, La carte de développement à ajouter.
     */
    public void addPurchasedCard(DevCard card){
        purchasedCards.add(card);
    }
    
    /**
     * Vérifie si le joueur peut acheter une carte de développement donnée.
     * paramètre: card, La carte à vérifier.
     * return: true si le joueur peut acheter la carte, false sinon.
     */
    public boolean canBuyCard(DevCard card){
        if (card == null){
            return false;
        }
        Resources cardCoast = card.getCost();
        int cardRes;
        int playerRes;
        for(Resource res: cardCoast.getAvailableResources()){
            cardRes = cardCoast.getNbResource(res);
            playerRes = getNbResource(res) + getResFromCards(res);
            if(playerRes < cardRes){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Permet au joueur de choisir une action à effectuer.
     * Méthode à implémenter dans les sous-classes.
     * return: Une instance d'Action représentant l'action choisie.
     * throws: IllegalArgumentException Si l'action choisie est invalide.
     */
    abstract Action chooseAction() throws IllegalArgumentException;
    /**
     * Permet au joueur de choisir les jetons à défausser.
     * Méthode à implémenter dans les sous-classes.
     * paramètre: nbTokenToDiscard, Le nombre de jetons à défausser.
     * return: Une instance de Resources représentant les jetons défaussés.
     * throws: IllegalArgumentException Si la sélection est invalide.
     */
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

        
        strPlayer[0] = "Player "+(id)+": "+name;
        strPlayer[1] = pointStr + "pts";
        strPlayer[2] = "";
        for(Resource res: Resource.values()){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            strPlayer[3+(Resource.values().length-1-res.ordinal())] = res.toSymbol() + " ("+resources.getNbResource(res)+") ["+getResFromCards(res)+"]";
        }
        
        return strPlayer;
    }
}
