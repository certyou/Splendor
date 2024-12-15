
/**
 * Décrivez votre classe DumbRobotPlayer ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
import java.util.Random;
import java.util.ArrayList;

public class DumbRobotPlayer extends Player
{
    Board board;
    
    public DumbRobotPlayer(int id, String name, Board board){
        super(id, name);
        this.board = board;
    }
    
    public Action chooseAction(){
        //Acheter une carte sur le plateau 
        Game.display.out.println("\n\n==== Tour de " + super.getName() + " ====");
        
        DevCard card;
        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                card = board.getCard(i,j);
                if(super.canBuyCard(card)){
                    Game.display.out.println("\nLe robot a acheté la carte suivante : " + card);
                    return new BuyCardAction(card);
                }
            }
        }
        
        //Acheter deux jetons ressources de même type
        int nbRes;
        for(Resource resource: board.getAvailableResources())
        {
            nbRes = board.getNbResource(resource);
            if(nbRes >= 4){
                Game.display.out.println("\nLe robot a récupéré les deux ressources suivantes : " + resource);
                return new PickSameTokensAction(resource);
            }
        }
        
        //Acheter des jetons ressources de type différents
        Resource res1 = null;
        Resource res2 = null;;
        Resource res3 = null;;
        for(Resource resource: board.getAvailableResources()){
           nbRes = board.getNbResource(resource);
           if(nbRes >= 1){
               if(res1 == null){
                   res1 = resource;
               }
               else{if(res2 == null){
                        res2 = resource;
                    }else{if(nbRes >= 1){
                        res3 = resource;
                        Game.display.out.println("\nLe robot a récupéré les trois ressources suivantes : " + res1 + ", " + res2 + " et " + res3);
                        return new PickDiffTokensAction(res1,res2,res3);
                        }    
                    }
               }
           }
        }
        
        //Passer son tour
        return new PassAction();
    }
    
    public Resources chooseDiscardingTokens(int nbTokenToDiscard){
        Random randomNumbers = new Random();
        ArrayList<Resource> recourceList = super.getAvailableResources();
        Resources resourcesRec = new Resources(0,0,0,0,0);
        Resource resource;
        int index, size = recourceList.size()-1;
        
        for(int i=0; nbTokenToDiscard == i; i++){
            index = randomNumbers.nextInt(size);
            resource = recourceList.get(index);
            resourcesRec.updateNbResource(resource, 1);
            if(super.getNbResource(resource) - resourcesRec.getNbResource(resource) <= 0){
                recourceList.remove(index);
            }
        }
        
        return resourcesRec;
    }
    
}
