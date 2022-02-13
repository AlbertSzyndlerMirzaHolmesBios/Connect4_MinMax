import java.awt.*;
import java.util.Collection;

public class GameState {
    private static final Color blueBit = new Color(0,0,255);
    private static final Color redBit = new Color(255,0,0);
    private static char[][] gridState = new char[7][6];

    public char[][] createGridState(Point[][] coords) throws AWTException {
        Robot r = new Robot();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                if (r.getPixelColor(coords[i][j].x,coords[i][j].y).getRGB() == blueBit.getRGB()){
                    gridState[i][j] = 'B';
                }else if (r.getPixelColor(coords[i][j].x,coords[i][j].y).getRGB() == redBit.getRGB()){
                    gridState[i][j] = 'R';
                }else{
                    gridState[i][j] = '0';
                }
            }
        }
        return gridState;
    }

}
