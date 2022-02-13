import com.sun.jdi.event.StepEvent;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Scanner;

//@todo Learn Minimax and create file that reads the gridstate then uses minmax to find the best move
//@todo ??
//@todo Profit




public class Calibration {
    static GameState game = new GameState();
    public static void main(String[] args) throws Exception {
        Point[][] grid = loadSavedData();
        char[][] gridState = game.createGridState(grid);
        for (char[] cur: gridState) {
            System.out.println(Arrays.toString(cur));
        }
    }

    public static Point[][] loadSavedData()throws Exception {
        Point[][] output = new Point[7][6];
        FileReader fr = new FileReader("saveCali.txt");
        Scanner read = new Scanner(fr);
        String[] fileData = new String[7];

        for (int i = 0; i < 7; i++) {
            fileData[i] = read.nextLine();
        }

        for (int i = 0; i < 7; i++) {
            String[] current = fileData[i].split("!");
            for (int j = 0; j < 6; j++) {
                String[] splitToCoords = current[j].split(",");
                output[i][j] = new Point(Integer.parseInt(splitToCoords[0]),Integer.parseInt(splitToCoords[1]));
            }
        }

        return output;
    }

    public static void saveCalibration(Point[][] grid) throws Exception{
        File saveCali = new File("saveCali.txt");
        String[] fileData = new String[7];
        for (int i = 0; i < 7; i++) {
            fileData[i] = "";
            for (int j = 0; j < 6; j++) {
                fileData[i] += grid[i][j].x + "," + grid[i][j].y + "!";
            }
        }
        FileWriter fw = new FileWriter("saveCali.txt");
        for (String current: fileData) {
            fw.write(current);
            fw.write("\n");
        }
        fw.close();
    }

    public static Point[][] firstCalibrate() throws Exception {
        Point[][] grid;
        Point[] firstBoxes = new Point[2];
        PointerInfo mouse  = MouseInfo.getPointerInfo();
        Point mousePos = new Point();
        System.out.println("Starting calibration, hover on top right box");
        for (int i = 0; i < 2; i++) {
            Thread.sleep(2000);
            mouse  = MouseInfo.getPointerInfo();
            mousePos = mouse.getLocation();
            firstBoxes[i] = mousePos;
            System.out.println("saved");
        }

        grid = calcBoxPos(firstBoxes);
        return grid;
        }
    public static Point[][] calcBoxPos(Point[] firstBoxes){
        Point[][] output = new Point[7][6];
        int diff = firstBoxes[1].x - firstBoxes[0].x;
        System.out.println(diff);
        System.out.println(Arrays.toString(firstBoxes));

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                int x = firstBoxes[0].x + (i * diff);
                int y = firstBoxes[0].y + (j * diff);
                output[i][j] = new Point(x,y);
            }
        }
        return output;
    }
}
