import java.util.Scanner;

public class Drwal {
    private static int xStart, yStart, width, height;
    private static char color;
    private static char[][] image;

    public static void main(String[] args){
        handleArgs(args);
        image = new char[height][width];
        handleStdIn();
        if(checkAvailability(xStart,yStart)) coloring(xStart,yStart);
        else err();
        draw();
    }

    private static void handleArgs(String[] args){
        try{
            xStart = Integer.parseInt(args[0]);
            yStart = Integer.parseInt(args[1]);
            width = Integer.parseInt(args[3]);
            height = Integer.parseInt(args[4]);
            if(width > 50 || width < 0) err();
            if(height > 50 || height < 0) err();
            if(xStart > width) err();
            if(yStart > height) err();
        }
        catch (NumberFormatException e){err();}

        xStart--;
        yStart--;

        if(args[2].length()!=1) err();
        color = args[2].charAt(0);
    }

    private static void handleStdIn(){
        Scanner scanner = new Scanner(System.in);
        String in; int rowCounter = 0;
        while(scanner.hasNextLine()){
            in = scanner.nextLine();
            rowCounter++;
            if(in.length() > width || rowCounter > height) err();
            System.arraycopy(in.toCharArray(),0,image[rowCounter-1],0,in.length());
        }
    }

    private static void coloring(int X, int Y){
        image[Y][X] = color;
        if(checkAvailability(X+1, Y)) coloring(X+1,Y);
        if(checkAvailability(X, Y+1)) coloring(X,Y+1);
        if(checkAvailability(X-1, Y)) coloring(X-1,Y);
        if(checkAvailability(X, Y-1)) coloring(X,Y-1);
    }

    private static boolean checkAvailability(int X, int Y){
        if(X < 0 || X >= width || Y < 0 || Y >= height) return false;
        else if(image[Y][X] == (char)32 || image[Y][X] == (char)0) return true;
        return false;
    }

    private static void draw(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < image[i].length ; j++) {
                if(Character.isWhitespace(image[i][j])) stringBuilder.append((char)32);
                else stringBuilder.append(image[i][j]);
            }
            stringBuilder.append('\n');
        }
        System.out.println(stringBuilder.toString());
    }

    private static void err(){
        System.out.println("klops");
        System.exit(0);
    }
}
