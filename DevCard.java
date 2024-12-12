 

public class DevCard implements Displayable {
    private int tier;
    private Resources cost;
    private int points;
    private Resource resourceType;

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
    
    public int getTier(){
        return tier;
    }
    
    public int getPoints(){
        return points;
    }
    
    public Resources getCost(){
        return cost;
    }
    
    public Resource getResourceType(){
        return resourceType;
    }
    
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
    
    public boolean equals(DevCard card){
        return tier == card.getTier()
        && points == card.getPoints()
        && resourceType == card.getResourceType()
        && cost.equals(card.getCost());
    }
}