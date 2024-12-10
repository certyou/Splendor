 
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
    private static final int ROWS_BOARD=36, ROWS_CONSOLE=8, COLS=82;
    public static final  Display display = new Display(ROWS_BOARD, ROWS_CONSOLE, COLS);
    
    private Board board;
    private ArrayList<Player> players;
    private Scanner scan = new Scanner(Game.display.in);
    private static int id; 

    public static void main(String[] args) throws IllegalArgumentException{
        //-- à modifier pour permettre plusieurs scénarios de jeu
        display.outBoard.println("Bienvenue sur Splendor !");
        Game game = new Game(2);
        game.play();
        display.close();
    }

    public Game(int nbOfPlayers) throws IllegalArgumentException 
    {
        if (nbOfPlayers<2 || nbOfPlayers>4) {
            throw new IllegalArgumentException();
        }
        
        board = new Board();
        players = new ArrayList<Player>();
        id = 1;
        
    
        String name;
        Game.display.out.println("Veuillez rentrer un nom : ");
        name = scan.next();
        HumanPlayer joueur1 = new HumanPlayer(id, name, board);
        players.add(joueur1);
        id+=1;
        
        
        
        for(int i = 0 ; i<nbOfPlayers-1;i++) {
            Game.display.out.println("Veuillez rentrer un nom : ");
            name = scan.next();
            DumbRobotPlayer robot = new DumbRobotPlayer(id, name, board);
            players.add(robot);
            id+=1;
        }
    }

    public int getNbPlayers(){
        return players.size(); 
    }

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
    
    public void play() throws IllegalArgumentException{
        boolean fin = false;
        while (fin != true) {
            for (int i=0; i<players.size();i++){
                for (int j=0; j<board.toStringArray().length;j++) {
                    Game.display.outBoard.println(board.toStringArray()[j]);
                }
                Game.display.outBoard.println();
                move(players.get(i));
                discardToken(players.get(i));
            }
            fin = isGameOver();
        }
        gameOver();
        
    }

    private void move(Player player) throws IllegalArgumentException{
        Action choix = player.chooseAction();
        choix.process(player, board);
    }

    private void discardToken(Player player){
        if (player.getNbTokens() > 10 ) {
            int n = player.getNbTokens() - 10;
            DiscardTokensAction choix = new DiscardTokensAction(n);
        }
    }

    public boolean isGameOver(){
        boolean res = false;
        for (int i = 0; i<getNbPlayers();i++) {
            if (players.get(i).getPoints() >= 15) {
                res = true;
            }
        }
        return res;
    }

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
    }


}
