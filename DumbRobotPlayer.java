
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
    public DumbRobotPlayer(int id, String name){
        super(id, name);
    }
    
    public Action chooseAction(Board board){
        //Acheter une carte sur le plateau 
        DevCard card;
        for(int i=0; i<4; i++){
            for(int j=0; i<4; i++){
                card = board.getCard(i,j);
                if(super.canBuyCard(card)){
                    return new BuyCardAction(card);
                }
            }
        }
        
        //Acheter deux jetons ressources de même type
        int nbRes;
        for(Resource resource: board.getAvailableResources())
        {
            nbRes = getNbResource(resource);
            if(nbRes >= 4){
                return new PickSameTokensAction(resource);
            }
        }
        
        //Acheter des jetons ressources de type différents
        Resource res1;
        Resource res2;
        for(Resource resource: board.getAvailableResources()){
           nbRes = getNbResource(resource);
           if(nbRes >= 1 && res1 == null){
               res1 = resource;
           }
           else{if(nbRes >= 1){
                    res2 = resource;
                    return new PickDiffTokensAction(res1,res2);
                }
           }
        }
        
        //Passer son tour
        return new PassAction();
    }
    
    public Resources chooseDiscardingTokens(int nbTokenToDiscard){
        Random randomNumbers = new Random();
        ArrayList<Resource> recourceList = super.getAvailableResources();
        Resources resources;
        Resource resource;
        int index, size = recourceList.size()-1;
        
        for(int nbTokenToDiscard; nbTokenToDiscard == 0; nbTokenToDiscard--){
            index = randomNumbers.nextInt(size);
            resource = recourceList.get(index);
            resources.updateNbResource(resources, 1);
            if(super.getNbResource(resource) - resources.getNbResource(resource) <= 0){
                recourceList.remove(index);
            }
        }
        
        return resources;
    }
    
}
