import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Collections;

// Définition de la classe Board, qui représente le plateau de jeu et implémente l'interface Displayable.
public class Board implements Displayable {
    // attribut de la classe
    private String filename;
    private Scanner scanner; // Scanner pour lire les données du fichier
    private ArrayList<Stack<DevCard>> stackCards;
    private DevCard[][] visibleCards;
    private Resources resources;
    
    /* constructeur de la classe Board
     * 
     * input : /
     * output : /
     * precondition :
     *  - possède un fichier stats.csv respectant un format précis (tier, coutDIAMOND, coutSAPPHIRE, coutEMERALD, coutRUBY, coutONYX, points, type)
     * postcondition :
     *  - initialise visibleCards, stackCards et resources
     * 
     */
    public Board(int nb_player){
        visibleCards = new DevCard[3][4];
        this.filename = "stats.csv";
        stackCards = new ArrayList<Stack<DevCard>>();
        stackCards.add(new Stack<DevCard>()); // noble
        stackCards.add(new Stack<DevCard>()); // tier 1
        stackCards.add(new Stack<DevCard>()); // tier 2
        stackCards.add(new Stack<DevCard>()); // tier 3
        if (nb_player == 2){
            resources = new Resources(4,4,4,4,4);
        } else if (nb_player == 3){
            resources = new Resources(5,5,5,5,5);
        } else {
            resources = new Resources(7,7,7,7,7);
        }
        
        
        try {
            scanner = new Scanner(new File(filename));
            String new_raw = scanner.nextLine(); // skip the first line
            while (scanner.hasNextLine()){
                new_raw = scanner.nextLine();
                String[] colonnes = new_raw.split(",");
                // Création d'une nouvelle carte DevCard à partir des données lues
                DevCard new_card = new DevCard(
                            Integer.parseInt(colonnes[0]), // tier
                            Integer.parseInt(colonnes[1]), // diammond
                            Integer.parseInt(colonnes[2]), // sapphire
                            Integer.parseInt(colonnes[3]), // emerald
                            Integer.parseInt(colonnes[4]), // ruby
                            Integer.parseInt(colonnes[5]), // onyx
                            Integer.parseInt(colonnes[6]), // points
                            colonnes[7] // type
                );    
                // Ajout de la carte dans la pile correspondante (selon son tier)
                stackCards.get(Integer.parseInt(colonnes[0])).push(new_card);
            }
        } catch (Exception e){
            System.out.println(e);
        }
        
        // Mélange les cartes de chaque piles
        for (Stack lib : stackCards){
            Collections.shuffle(lib);
        }
        
        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                visibleCards[i][j] = drawCard(3-i);
            }
        }
    }

    /* --- Stringers --- */
    /* stringer pour les decks de tier
     * 
     * input :
     *  - tier | int
     * output :
     *  - deckStr | String
     * precondition :
     * - tier doit être compris entre 1 et 3
     * postcondition : /
     * 
     */
    private String[] deckToStringArray(int tier){
        /** EXAMPLE
         * ┌────────┐
         * │        │╲ 
         * │ reste: │ │
         * │   16   │ │
         * │ cartes │ │
         * │ tier 3 │ │
         * │        │ │
         * └────────┘ │
         *  ╲________╲│
         */
        int nbCards = stackCards.get(tier).size();
        
        String[] deckStr = {"\u250C\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2510  ",
                            "\u2502        \u2502\u2572 ",
                            "\u2502 reste: \u2502 \u2502",
                            "\u2502   "+String.format("%02d", nbCards)+"   \u2502 \u2502",
                            "\u2502 carte"+(nbCards>1 ? "s" : " ")+" \u2502 \u2502",
                            "\u2502 tier "+tier+" \u2502 \u2502",
                            "\u2502        \u2502 \u2502",
                            "\u2514\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2518 \u2502",
                            " \u2572________\u2572\u2502"};
        return deckStr;
    }

    private String[] resourcesToStringArray(){
        /** EXAMPLE
         * Resources disponibles : 4♥R 4♣E 4♠S 4♦D 4●O
         */
        String[] resStr = {"Resources disponibles : "};
        
        for(Resource res : Resource.values()){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            resStr[0] += resources.getNbResource(res)+res.toSymbol()+" ";
        }
        resStr[0] += "        ";
        return resStr;
    }

    private String[] boardToStringArray(){
        String[] res = Display.emptyStringArray(0, 0);

        //Deck display
        String[] deckDisplay = Display.emptyStringArray(0, 0);
        for(int i=stackCards.size()-1;i>0;i--){
            deckDisplay = Display.concatStringArray(deckDisplay, deckToStringArray(i), true);
        }

        //Card display
        String[] cardDisplay = Display.emptyStringArray(0, 0);
        for(int i=0;i<3;i++){ //-- parcourir les différents niveaux de carte (i)
            String[] tierCardsDisplay = Display.emptyStringArray(8, 0);
            for(int j=0;j<4;j++){ //-- parcourir les 4 cartes faces visibles pour un niveau donné (j)
                tierCardsDisplay = Display.concatStringArray(tierCardsDisplay, visibleCards[i][j]!=null ? visibleCards[i][j].toStringArray() : DevCard.noCardStringArray(), false);
            }
            cardDisplay = Display.concatStringArray(cardDisplay, Display.emptyStringArray(1, 40), true);
            cardDisplay = Display.concatStringArray(cardDisplay, tierCardsDisplay, true);
        }
        
        res = Display.concatStringArray(deckDisplay, cardDisplay, false);
        res = Display.concatStringArray(res, Display.emptyStringArray(1, 52), true);
        res = Display.concatStringArray(res, resourcesToStringArray(), true);
        res = Display.concatStringArray(res, Display.emptyStringArray(35, 1, " \u250A"), false);
        res = Display.concatStringArray(res, Display.emptyStringArray(1, 54, "\u2509"), true);
        
        return res;
    }

    @Override
    public String[] toStringArray() {
        return boardToStringArray();
    }
    
    public int getNbResource(Resource res){
        return resources.getNbResource(res);
    }
    
    public void setNbResource(Resource res, int new_value){
        resources.setNbResource(res, new_value);
    }

    public void updateNbResource(Resource resource, int v){
        resources.updateNbResource(resource, v);
    }
    
    public ArrayList<Resource> getAvailableResources(){
        return resources.getAvailableResources();
    }
    
    public DevCard getCard(int i, int j){
        return visibleCards[i][j];
    }
    
    public void updateCard(DevCard old_card){
        int cpt=0;
        for (DevCard card : visibleCards[3-old_card.getTier()]){
            System.out.println(card.equals(old_card));
            if (card.equals(old_card)){
                if (stackCards.get(old_card.getTier()).size() == 0){
                    visibleCards[3-old_card.getTier()][cpt] = null;
                } else {
                    visibleCards[3-old_card.getTier()][cpt] = drawCard(old_card.getTier());
                }
            }
            cpt++;
        }
        
    }
    
    public void updateCard(int i, int j){
        if (visibleCards[i].length == 0){
            visibleCards[i][j] = null;
        } else {
            visibleCards[i][j] =  drawCard(i);
        }
    }
    
    public DevCard drawCard(int tier){
        return stackCards.get(tier).pop();
    }
    
    public boolean canGiveSameTokens(Resource res){
        return getNbResource(res) >= 4;
    }
    
    public boolean canGiveDiffTokens (ArrayList<Resource> res_wanted){
        ArrayList<Resource> res_dispo = getAvailableResources();
        for (Resource res :res_wanted){
            if (!res_dispo.contains(res)){
                return false;
            }
        }
        return true;
    }
}
