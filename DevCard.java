public class DevCard implements Displayable {
    private int tier;
    private Resources cost;
    private int points;
    private Resource resourceType;
    
    /* constructeur de la classe DevCard
     * 
     * input :
     *  - tier | int
     *  - coutDIAMMOND | int
     *  - coutSAPPHIRE | int
     *  - coutEMERALD | int
     *  - coutRUBY | int
     *  - coutONYX | int
     *  - points | int
     *  - type | String
     * output : /
     * precondition : /
     * postcondition :
     *  - initialise tier, cost et points
     * 
     */
    public DevCard(int tier, int coutDIAMMOND, int coutSAPPHIRE, int coutEMERALD, int coutRUBY, int coutONYX, int points, String type)
    {
        this.tier = tier;
        this.cost = new Resources(coutDIAMMOND, coutSAPPHIRE, coutEMERALD, coutRUBY, coutONYX);
        this.points = points;
        System.out.println(type);
        switch(type) {
          case "DIAMOND":
            this.resourceType = Resource.DIAMOND;
            break;
          case "SAPPHIRE":
            this.resourceType = Resource.SAPPHIRE;
            break;
        case "EMERALD":
            this.resourceType = Resource.EMERALD;
            break;
        case "RUBY":
            this.resourceType = Resource.RUBY;
            break;
        case "ONYX":
            this.resourceType = Resource.ONYX;
            break;
        }
    }
    
    /* getter pour le tier
     * 
     * input : /
     * output :
     *  - tier | int
     * precondition : /
     * postcondition : /
     * 
     */
    public int getTier(){
        return tier;
    }
    
    /* getter pour les points
     * 
     * input : /
     * output :
     *  - points | int
     * precondition : /
     * postcondition : /
     * 
     */
    public int getPoints(){
        return points;
    }
    
    /* getter pour le cout
     * 
     * input : /
     * output :
     *  - cost | Ressources
     * precondition : /
     * postcondition : /
     * 
     */
    public Resources getCost(){
        return cost;
    }
    
    /* getter pour le type
     * 
     * input : /
     * output :
     *  - resourceType | Ressource
     * precondition : /
     * postcondition : /
     * 
     */
    public Resource getResourceType(){
        return resourceType;
    }
    
    /* stringer de la carte
     * 
     * input : /
     * output :
     *  - cardStr | String
     * precondition : /
     * postcondition : /
     * 
     */
    public String[] toStringArray(){
        /** EXAMPLE
         * ┌────────┐
         * │①    ♠S│
         * │        │
         * │        │
         * │2 ♠S    │
         * │2 ♣E    │
         * │3 ♥R    │
         * └────────┘
         */
        String pointStr = "  ";
        if(getPoints()>0){
            pointStr = new String(new int[] {getPoints()+9311}, 0, 1);
        }
        String[] cardStr = {"\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510",
                            "\u2502"+pointStr+"    "+resourceType.toSymbol()+"\u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2502        \u2502",
                            "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518"};
        //update cost of the repr
        int i=6;
        
        for(Resource res : Resource.values()){ //-- parcourir l'ensemble des resources (res)en utilisant l'énumération Resource
            if(getCost().getNbResource(res)>0){
                cardStr[i] = "\u2502"+getCost().getNbResource(res)+" "+res.toSymbol()+"    \u2502";
                i--;
            }
        }
        
        return cardStr;
    }
    
    /* stringer de la carte (si null)
     * 
     * input : /
     * output :
     *  - cardStr | String
     * precondition : /
     * postcondition : /
     * 
     */
    public static String[] noCardStringArray(){
        /** EXAMPLE
         * ┌────────┐
         * │ \    / │
         * │  \  /  │
         * │   \/   │
         * │   /\   │
         * │  /  \  │
         * │ /    \ │
         * └────────┘
         */
        String[] cardStr = {"\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510",
                            "\u2502 \\    / \u2502",
                            "\u2502  \\  /  \u2502",
                            "\u2502   \\/   \u2502",
                            "\u2502   /\\   \u2502",
                            "\u2502  /  \\  \u2502",
                            "\u2502 /    \\ \u2502",
                            "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518"};
        
        return cardStr;
    }
    
    /* toString, résumer de la carte
     * 
     * input : /
     * output :
     *  - cardStr | String
     * precondition : /
     * postcondition : /
     * 
     */
    public String toString(){
        String cardStr = "";
        cardStr = getPoints()+"pts, type "+resourceType.toSymbol()+" | coût: ";
        for(Resource res : Resource.values()){ 
            if(getCost().getNbResource(res)>0){
                cardStr += getCost().getNbResource(res)+res.toSymbol()+" ";
            }
        }
        return cardStr;
    }
    
    /* equals, vérifie si la carte en argument est la même que l'objet
     * 
     * input :
     *  - card | DevCard
     * output :
     *  - boolean
     * precondition : /
     * postcondition : /
     * 
     */
    public boolean equals(DevCard card){
        return tier == card.getTier()
        && points == card.getPoints()
        && resourceType == card.getResourceType()
        && cost.equals(card.getCost());
    }
}