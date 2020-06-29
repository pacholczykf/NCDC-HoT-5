public class Kosmolot {
    private static short rocketSize;
    private static boolean armored;
    private static char[][] segment;
    private static char[][] bottomSegment;
    private static char[] topSegment;

    public static void main(String[] args){
        handleArgs(args);
        buildSegments();
        drawRocket();
    }

    private static void handleArgs(String[] args){
        if(args.length!=2) err();
        try{
            rocketSize = Short.parseShort(args[0]);
            if(rocketSize > 75 || rocketSize < 0) err();
        }
        catch (NumberFormatException e){err();}
        if(args[1].length()!=1) err();
        char c = args[1].toCharArray()[0];
        if(c == 'Y') armored = true;
        else if(c == 'N') armored = false;
        else err();
    }

    private static void buildSegments()
    {
        segment = new char[rocketSize][rocketSize];
        for (int i = 0; i <rocketSize ; i++) {
            for (int j = 0; j < rocketSize; j++) {
                if(j<=i) segment[i][j] = '*';
                else segment[i][j] = ' ';
            }
        }

        if (armored){
            for (int i = 0; i < rocketSize - 1 ; i++) {
                segment[i][i] = '\\';
            }
            bottomSegment = new char[rocketSize][rocketSize];
            for (int i = 0; i < rocketSize; i++) {
                System.arraycopy(segment[i], 0, bottomSegment[i], 0, rocketSize);
            }
            for (int i = 0; i < rocketSize; i++) {
                bottomSegment[i][0] = '>';
            }
            topSegment = new char[rocketSize];
            System.arraycopy(segment[rocketSize-1], 0, topSegment, 0, rocketSize);
            topSegment[rocketSize-1] = '>';
        }
    }

    private static void reversedSegments(){
        for (int i = 0; i < rocketSize - 1 ; i++) {
            segment[i][i] = '/';
            if(i!=0) bottomSegment[i][i] = '/';
        }
    }

    private static void drawRocket(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < rocketSize ; i++) {
            for (int j = 0; j < rocketSize ; j++) {
                if(armored && j == 0) stringBuilder.append(bottomSegment[i]);
                else if(armored && j == rocketSize - 1 && i == rocketSize - 1)
                    stringBuilder.append(topSegment);
                else stringBuilder.append(segment[i]);
            }
            stringBuilder.append('\n');
        }

        if(armored)reversedSegments();

        for (int i = rocketSize - 2; i >= 0 ; i--) {
            for (int j = 0; j < rocketSize ; j++) {
                if(armored && j == 0) stringBuilder.append(bottomSegment[i]);
                else stringBuilder.append(segment[i]);
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
