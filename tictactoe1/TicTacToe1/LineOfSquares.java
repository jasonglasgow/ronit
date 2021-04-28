import java.util.List;

public class LineOfSquares {
      private static final String NEITHER = "Neither";
      // should be ImmutableList
      private final List<TTTSquare> squares;
      private String winner;
   
   public LineOfSquares(List<TTTSquare> squares) {      
      this.squares = squares;
      winner = NEITHER;
   }
   
   public String getWinner(){return winner;}
   
   public boolean hasWinner(){
      return (!winner.equals(NEITHER));
   }
   
   public boolean isFilled(){
      for (TTTSquare square : squares){
         if (!square.isMarked()){
            return false;
         }
      }
      return true;
   }
   
   /** 
    * sets winner field if every square has same text
    */
   public void adjustWinner(){
      String text = null;
      for (TTTSquare square : squares) {
         String squareText = square.getText();
         if (squareText == null || squareText.isEmpty()) {
            return;
         }
         if (text == null) {
            text = squareText;
         }
         else if (!squareText.equals(text)) {
            return;
         }

      }
      winner = text;
   }
  
}