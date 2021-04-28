//import java.awt.event.*;  
import java.awt.Font;

import javax.swing.*;

public class TTTSquare extends JButton {

   private final int column;
   
   public TTTSquare(int column) {
      super();
      this.column = column;
      this.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 120));
      
   }
   
   public boolean isMarked(){
      if (this.getText().equals("")){return false;}
      return true;
      }


   public int getColumn() {
      return column;
   }
   
   

}
