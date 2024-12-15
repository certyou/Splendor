 
/*
 * @author    Corentin Dufourg
 * @version     1.1
 * @since       1.0
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException; 

public class Game extends Exception {
    /* L'affichage et la lecture d'entrée avec l'interface de jeu se fera entièrement via l'attribut display de la classe Game.
     * Celui-ci est rendu visible à toutes les autres classes par souci de simplicité.
     * L'intéraction avec la classe Display est très similaire à celle que vous auriez avec la classe System :
     *    - affichage de l'état du jeu (méthodes fournies): Game.display.outBoard.println("Nombre de joueurs: 2");
     *    - affichage de messages à l'utilisateur: Game.display.out.println("Bienvenue sur Splendor ! Quel est ton nom?");
     *    - demande d'entrée utilisateur: new Scanner(Game.display.in);
     */
    
    //Déclaration des attributs
    private static final int ROWS_BOARD=36, ROWS_CONSOLE=8, COLS=82;
    public static final  Display display = new Display(ROWS_BOARD, ROWS_CONSOLE, COLS);
    
    private Board board;
    private ArrayList<Player> players;
    private Scanner scan = new Scanner(Game.display.in);
    private static int id; 

    /**
     * Méthode permettant de lancer une partie :
     * 
     * Création d'un objet Game
     * Gestion de la fermeture de la fenêtre de jeu
     *
     */
    public static void main(String[] args) throws IllegalArgumentException{
        int choice;
        Scanner scanner = new Scanner(Game.display.in);
        
        Game.display.out.println("Bienvenue sur Splendor !\nChoisissez le nombre de joueur (entre 2 et 4): \n");
        choice = scanner.nextInt();
        Game game = new Game(choice);
        Game.display.out.println("\n===== Debut de la partie =====");
        game.play();
        display.close();
    }
    
    /**
     * Constructeur de la classe Game.
     * Initialise une partie du jeu
     *
     * parametre: nbOfPlayer,    Nombre joueurs.
     */
    public Game(int nbOfPlayers) throws IllegalArgumentException 
    {
        
        //Gestion de l'erreur pour que le nombre de joueur soit entre 2 et 4
        if (nbOfPlayers<2 || nbOfPlayers>4) {
            throw new IllegalArgumentException();
        }
        
        //Création du plateau de jeu, de la liste contenant les joueurs et de l'id des joueurs
        board = new Board(nbOfPlayers);
        players = new ArrayList<Player>();
        id = 1;
        
        //Récupération des noms donné par les joueurs et création des objets HuamnPlayer et DumbRobotPlayer
        String name;
        Game.display.out.println("Joueur " + id + " veuillez rentrer un nom : ");
        name = scan.next();
        HumanPlayer joueur1 = new HumanPlayer(id, name, board);
        players.add(joueur1);
        id+=1;
        
        for(int i = 0 ; i<nbOfPlayers-1;i++) {
            Game.display.out.println("Veuillez rentrer un nom (Robot): ");
            name = scan.next();
            DumbRobotPlayer robot = new DumbRobotPlayer(id, name + " (Robot)", board);
            players.add(robot);
            id+=1;
        }
    }
    
    /**
     * Accesseur de l'attribut nbOfPlayers
     *
     * return: int, Nombre de joueurs
     */
    public int getNbPlayers(){
        return players.size(); 
    }

    /**
     * Méthode permettant l'affichage du plateau de jeu
     *
     * paramètre : int, id du joueur qui joue
     */
    private void display(int currentPlayer){
        String[] boardDisplay = board.toStringArray();
        String[] playerDisplay = Display.emptyStringArray(0, 0);
        for(int i=0;i<players.size();i++){
            String[] pArr = players.get(i).toStringArray();
            if(i==currentPlayer){
                pArr[0] = "\u27A4 " + pArr[0];
            }
            playerDisplay = Display.concatStringArray(playerDisplay, pArr, true);
            playerDisplay = Display.concatStringArray(playerDisplay, Display.emptyStringArray(1, COLS-54, "\u2509"), true);
        }
        String[] mainDisplay = Display.concatStringArray(boardDisplay, playerDisplay, false);

        display.outBoard.clean();
        display.outBoard.println(String.join("\n", mainDisplay));
    }
    
    /**
     * Méthode gérant le déroulement de la partie
     *
     * throws : IllegalArgumentException
     */
    public void play() throws IllegalArgumentException{
        
        boolean fin = false;
        while (fin != true) {
            //Les joueurs jouent à tour de rôle
            for (int i=0; i<players.size();i++){
                
                //Affichage des informations des joueurs
                display(players.get(i).getId());
                
                //Affichage du plateau de jeu
                Game.display.outBoard.println();
                
                //Action du joueur
                move(players.get(i));
                
                //Test si le joueus a plus de 10 tokens
                discardToken(players.get(i));
            }
            
            //Test de fin de partie 
            fin = isGameOver();
        }
        //Affichage message de fin de partie 
        gameOver();
        
    }

    /**
     * Méthode permettant de gérer le tour d'un joueur
     *
     * paramètre : Player, joueur jouant son tour
     */
    private void move(Player player) throws IllegalArgumentException{
        
        //Récupération du choix du joueur
        Action choix = player.chooseAction();
        
        //Action du joueur
        choix.process(player, board);
    }

    /**
     * Méthode permettant de gérer le test si le joueur à plus de 10 tokens
     *
     * paramètre : Player, joueur jouant son tour
     */
    private void discardToken(Player player) throws IllegalArgumentException {
        if (player.getNbTokens() > 10 ) {
            
            //Calcul du nombre de tokens à enlever
            int n = player.getNbTokens() - 10;
            
            //Les tokens sont rendus
            DiscardTokensAction choix = new DiscardTokensAction(n);
            choix.process(player, board);
        }
    }

    /**
     * Méthode permettant de tester si la partie est finie
     *
     * return boolean, true ou false en fonction de si la partie est finie
     */
    public boolean isGameOver(){
        boolean res = false;
        for (int i = 0; i<getNbPlayers();i++) {
            if (players.get(i).getPoints() >= 15) {
                res = true;
            }
        }
        return res;
    }

    /**
     * Méthode permettant d'afficher le ou les gagnants
     *
     */
    private void gameOver(){
        String gagnant  = "";
        for (int i = 0; i<getNbPlayers();i++) {
            if (players.get(i).getPoints() >= 15) {
                if (gagnant == "") {
                    gagnant = players.get(i).getName();
                }else{
                    gagnant += " et ";
                    gagnant += players.get(i).getName();
                }
            }
        }
        Game.display.outBoard.println("Bravo à " + gagnant +" vous avez gagné");
        System.out.println("Bravo à " + gagnant +" vous avez gagné");
    }


}
