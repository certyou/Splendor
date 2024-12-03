import java.io.File;
import java.io.FileNotFoundException;
import java.util.Stack;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Collections;

public class Board implements Displayable {
    private String filename;
    private Scanner scanner;
    private ArrayList<Stack> tiers;
    private ArrayList<ArrayList<DevCard>> visibleCards;
    private Resources resources;
    
    public Board(){
        this.filename = "stats.csv";
        tiers = new ArrayList<Stack>();
        tiers.add(new Stack()); // noble
        tiers.add(new Stack()); // t1
        tiers.add(new Stack()); 
        tiers.add(new Stack());
        visibleCards = new ArrayList<ArrayList<DevCard>>();
        resources = new Resources(0,0,0,0,0);
        try {
            scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()){
                String new_raw = scanner.nextLine();
                String[] colonnes = new_raw.split(",");
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
                
                tiers.get(Integer.parseInt(colonnes[0])).push(new_card);
            }
        } catch (Exception e){
            System.out.println("fichier introuvable");
        }
        
        // shuffle cards
        for (Stack lib : tiers){
            Collections.shuffle(lib);
        }
    }

    /* --- Stringers --- */

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
        int nbCards = tiers.get(tier).size();
        
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
        /*
         * A decommenter
        for(ACOMPLETER){ //-- parcourir l'ensemble des resources (res) en utilisant l'énumération Resource
            resStr[0] += resources.getNbResource(res)+res.toSymbol()+" ";
        }
                 */
        resStr[0] += "        ";
        return resStr;
    }

    private String[] boardToStringArray(){
        String[] res = Display.emptyStringArray(0, 0);
        /*
         * 

        //Deck display
        String[] deckDisplay = Display.emptyStringArray(0, 0);
        for(int i=stackCards.size();i>0;i--){
            deckDisplay = Display.concatStringArray(deckDisplay, deckToStringArray(i), true);
        }

        //Card display
        String[] cardDisplay = Display.emptyStringArray(0, 0);
        for(ACOMPLETER){ //-- parcourir les différents niveaux de carte (i)
            String[] tierCardsDisplay = Display.emptyStringArray(8, 0);
            for(ACOMPLETER){ //-- parcourir les 4 cartes faces visibles pour un niveau donné (j)
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
                 */
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
    
    public ArrayList<Resource> getAvailableResources(){
        return resources.getAvailableResources();
    }
    
    public DevCard getCard(int i, int j){
        return visibleCards.get(i).get(j);
    }
}
