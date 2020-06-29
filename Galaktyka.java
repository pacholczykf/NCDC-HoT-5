public class Galaktyka {
    private static short telescopeSize;
    private static char orientation;
    private static int Xsize, Ysize, galacticLength;
    private static boolean[][] galactic;

    public static void main(String[] args){
        handleArgs(args);
        buildGalactic();
        drawGalactic();
    }

    private static void handleArgs(String[] args){
        if(args.length!=1) err();

        try{
            telescopeSize = Short.parseShort(args[0].substring(0,args[0].length()-1));
            if(telescopeSize > 10000 || telescopeSize < 0) err();
        }
        catch (NumberFormatException e){err();}
        char c = args[0].toCharArray()[args[0].length()-1];
        if(c == 'N' || c == 'E' || c == 'S' || c=='W') orientation = c;
        else err();
    }

    private static void buildGalactic(){
        if(orientation == 'E' || orientation == 'W') {
            Xsize = telescopeSize + 3;
            Ysize = telescopeSize + 2;
        }
        else{
            Xsize = telescopeSize + 2;
            Ysize = telescopeSize + 3;
        }
        galactic = new boolean[Xsize][Ysize];

        for (int i = 0; i < Xsize; i++) {
            for (int j = 0; j < Ysize; j++) {
                galactic[i][j] = false;
            }
        }

        boolean nextAvailable = true;
        int X,Y, direction;
        if(orientation == 'N'){ X = 0; Y = Ysize - 2; direction = 1; }
        else if(orientation == 'E'){ X = Xsize - 2; Y = Ysize - 1; direction = 2; }
        else if(orientation == 'S'){ X = Xsize - 1; Y = 1; direction = 3; }
        else { X = 1; Y = 0; direction = 0; }

        galacticLength = 0;

        while (nextAvailable){
            galactic[X][Y] = true;
            galacticLength++;

            if(!checkAvailability(X,Y,direction)){
                direction = switchDirection(direction);
                if(!checkAvailability(X,Y,direction)) nextAvailable = false;
            }

            switch (direction){
                case 0: Y=Y+1;break;
                case 1: X=X+1;break;
                case 2: Y=Y-1;break;
                case 3: X=X-1;break;
                default: err();
            }
        }
    }

    private static boolean checkAvailability(int X, int Y, int availabilityDirection){
        switch (availabilityDirection){
            case 0:
                if (Y > Ysize - 3) return false;
                if (galactic[X][Y+2]) return false;
                break;
            case 1:
                if (X > Xsize - 3) return false;
                if (galactic[X+2][Y]) return false;
                break;
            case 2:
                if (Y < 2) return false;
                if (galactic[X][Y-2]) return false;
                break;
            case 3:
                if (X < 2) return false;
                if (galactic[X-2][Y]) return false;
                break;
        }
        return true;
    }

    private static int switchDirection(int direction){
        direction++;
        if (direction > 3) direction = 0;
        return direction;
    }

    private static void drawGalactic(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < Xsize; i++) {
            for (int j = 0; j < Ysize ; j++) {
                if (galactic[i][j]) stringBuilder.append(' ');
                else stringBuilder.append('*');
            }
            stringBuilder.append('\n');
        }
        System.out.println(stringBuilder.toString());
        System.out.print(galacticLength);
    }

    private static void err(){
        System.out.println("klops");
        System.exit(0);
    }
}
