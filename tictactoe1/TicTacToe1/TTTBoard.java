import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TTTBoard {
  
   private final List<LineOfSquares> squareLists;
   private String bigWinner = "Neither";

   public TTTBoard(ArrayList<TTTSquare> squares) {
      squareLists = new ArrayList<LineOfSquares>();
      // make lineOfSquares for the 3 rows
      for (int row = 0; row < 3; ++row) {
         squareLists.add(new LineOfSquares(squares.subList(row * 3, (row +1) * 3 - 1)));
      }
      // make lineOfSquares for the 3 columns
      for (int column = 0; column < 3; ++column) {
         int theColumn = column;
         squareLists.add(new LineOfSquares(squares.stream()
         .filter(square -> square.getColumn() == theColumn)
         .collect(Collectors.toList())));
      }
      
   }
   
   public void adjustAllSets(){
      for (LineOfSquares line : squareLists) {
         line.adjustWinner();
         if (line.hasWinner()) {bigWinner = line.getWinner();}
      }
   }   
   
   public boolean hasBigWinner(){
      return (!bigWinner.equals("Neither"));
   }
   
   
   public String getBigWinner(){return bigWinner;}

}