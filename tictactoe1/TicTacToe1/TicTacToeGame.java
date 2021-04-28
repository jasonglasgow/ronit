import java.awt.event.*;  
import javax.swing.*;
import java.util.ArrayList;

public class TicTacToeGame {


      private static final int WIDTH = 200;
      private boolean playerTurnX = true;
      private String playerTurn = "X"; 
      private final JFrame frame = new JFrame();
      private final ArrayList<TTTSquare> squares = new ArrayList<TTTSquare>();
      private final TTTBoard board;
      private JTextField label = new JTextField(30);      
            
   public TicTacToeGame() {
      for (int i = 0; i<9; ++i) {
         squares.add(new TTTSquare(i % 3));
      }
      board = new TTTBoard(squares);
   }
   
   public void playGame(){
      this.createBoard();
      this.takeTurns();
   }
          
   public void createBoard(){
      label.setEditable(false);
      label.setText("X goes first");
      label.setBounds(0,600,300,70);
      frame.add(label);


      for (int i = 0; i < 9; ++i) {
         TTTSquare square = squares.get(i);
         int x = i % 3;
         int y = i / 3;
         square.setBounds(x * WIDTH, y*WIDTH, WIDTH, WIDTH);
      }
      
      for (TTTSquare square : squares) {
         frame.add(square);
      }
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000,1000);  
      frame.setLayout(null);  
      frame.setVisible(true);
   }
   
   public void takeTurns(){
      for (TTTSquare square : squares) {
         square.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
               square.setText(getPlayerTurn());
               square.setEnabled(false);
               board.adjustAllSets(); //this calls checking if each set has a winner
               System.out.println(board.getBigWinner());//testing
               //testSet.adjustWinner();
               //System.out.println(testSet.getWinner());
               if (board.hasBigWinner()){
                  System.out.println("GAME OVER -- Congrats " + board.getBigWinner());  
                  //call GameOver/close window??
                  
               }
               changePlayerTurn();
               label.setText("Next Player: " + getPlayerTurn());
               if (isFull()) {
                  label.setText("Game Over -- Tied game. Please close the window.");
               } 
            }  
         });
      }     
   }
   
   
   public boolean isFull(){
      for (TTTSquare square : squares) {
         if (square.getText().equals("")) {return false;}
         }
      return true;
   }

   public String getPlayerTurn(){
      return playerTurn;
   }
      
   public void changePlayerTurn(){
      playerTurnX = !playerTurnX;
      if (playerTurnX){playerTurn = "X";}
      if (!playerTurnX){playerTurn = "O";}
      //System.out.println("Next player: " + playerTurn);
      }
   
}