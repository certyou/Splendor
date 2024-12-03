import java.util.HashMap;
import java.util.ArrayList;
import java.lang.Integer;
/*
 * permet de stocker le prix des cartes + la quantité de jeton dans la banque
 */
public class Resources extends HashMap<Resource, Integer> {
    private HashMap<Resource, Integer> cout;
    public final int NUMBEROFRESOURCE = 5;
    
    /* constructeur de Resources
     * 
     * input :
     * - coutDIAMOND | cout en diamand de la carte
     * - coutSAPPHIRE| cout en saphire de la carte
     * - coutEMERALD | cout en emeraude de la carte
     * - coutRUBY    | cout en ruby de la carte
     * - coutONYX    | cout en onyx de la carte
     * 
     * precondition :
     * - coutDIAMOND >= 0
     * - coutSAPPHIRE >= 0
     * - coutEMERALD >= 0
     * - coutRUBY >= 0
     * - coutONYX >= 0
     * postcondition :
     * 
     */
    public Resources(int coutDIAMOND, int coutSAPPHIRE, int coutEMERALD, int coutRUBY, int coutONYX) {
        super();
        // préconditions
        if (coutDIAMOND<0) {
            System.out.println("ERROR : Resources-coutDIAMOND");
        }
        if (coutSAPPHIRE<0) {
            System.out.println("ERROR : Resources-coutSAPPHIRE");
        }
        if (coutEMERALD<0) {
            System.out.println("ERROR : Resources-coutEMERALD");
        }
        if (coutRUBY<0) {
            System.out.println("ERROR : Resources-coutRUBY");
        }
        if (coutONYX<0) {
            System.out.println("ERROR : Resources-coutONYX");
        }
        // corps
        super.put(Resource.DIAMOND, coutDIAMOND);
        super.put(Resource.SAPPHIRE, coutSAPPHIRE);
        super.put(Resource.EMERALD, coutEMERALD);
        super.put(Resource.RUBY, coutRUBY);
        super.put(Resource.ONYX, coutONYX);
    }
    
    /* Renvoi le nombre de ressource disponible
     * 
     * input : 
     * - ressource | une ressource
     * output : int | le nombre de cette ressource
     * 
     * precondition :
     * - ressource != null
     * postcondition :
     * 
     */
    public int getNbResource(Resource ressource) {
        // préconditions
        if (ressource == null) {
            System.out.println("ERROR : getNbResource");
        }
        // corps
        return super.get(ressource);  
    }
    
    /* Setter pour les ressources
     * 
     * input :
     * - ressource | une ressource
     * - new_value | une nouvelle quantité pour la dite ressource
     * output :
     * 
     * precondition :
     * - ressource != null
     * - new_value >= 0
     * postcondition :
     * 
     */
    public void setNbResource(Resource ressource, int new_value) {
        // préconditions
        if (ressource == null) {
             System.out.println("ERROR : setNbResource-ressource");
        }
        if (new_value < 0) {
            System.out.println("ERROR : setNbResource-new_value");
        }
        // corps
        super.put(ressource, new_value);
    }
    
    /* retire {v} a la ressource {ressource}
     * 
     * input : 
     * - ressource | une ressource
     * output : 
     * 
     * precondition :
     * - ressource != null
     * - v != 0
     * - |v| < |quantite de la dite ressource|
     * postcondition :
     * - ressource ne peut etre inférieur a 0
     */
    public void updateNbResource(Resource ressource, int v) {
        v = Math.abs(v); // valeur absolue de v
        // préconditions
        if (ressource == null) {
             System.out.println("ERROR : updateNbResource-ressource");
        }
        if (v == 0 || v>super.get(ressource)) {
            System.out.println("ERROR : updateNbResource-new_value");
        }
        // corps
        super.put(ressource, super.get(ressource)-v);
        // postconditions
        if (super.get(ressource) < 0) {
            super.put(ressource, 0);
        }
    }
    
    /* renvoi la liste des ressources qui sont disponible
     * 
     * input : 
     * output : liste des Ressources disponible
     * precondition :
     * postcondition :
     */
    public ArrayList<Resource> getAvailableResources() {
        // corps
        ArrayList<Resource> result = new ArrayList<Resource>();
        for (Resource r: Resource.values()) {
            if (super.get(r) > 0) {
                result.add(r);
            }
        }
        return result;
    }
    
    public boolean equals(Resources res){
        for (Resource r : Resource.values()){
            if (super.get(r) != super.get(res)){
                return false;
            }
        }
        return true;
    }
}