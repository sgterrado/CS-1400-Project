import java.io.*;
import java.util.Scanner;

//Handles File Management
class ReadingWriting {
    private final String TEXT_FILE = "wordFile.txt";
    private boolean haveGAD; //Ask questions about anxiety (Generalized Anxiety Disorders)?
    private boolean haveDep; //As questions about depression?

    //Constructor
    public ReadingWriting() {
        haveGAD = false; haveDep = false;
    }

    public void emptyF(UI ui){
        //UI has System.in scanner so we use it to ask whether user has GAD or depression
        int setUp = ui.setUp(); //Will be 1, 2, or 3
        if (setUp != 2) {haveGAD = true;}
        if (setUp != 1) {haveDep = true;}
    }

    //Angela: Reads array from file
    public EntryList readF(UI ui){
        EntryList ret = new EntryList(Main.NUM_QUESTIONS);
        //open file
        FileInputStream fileByteStream = null;
        try {fileByteStream = new FileInputStream(TEXT_FILE);}
        catch (FileNotFoundException e) {
            System.out.println("ERROR: Try fixing the file path in ReadingWriting.");
            e.printStackTrace();
        }
        try {
            Scanner inScnr = new Scanner(fileByteStream);

            //IF FILE IS EMPTY: setup
            if (!inScnr.hasNext()) { emptyF(ui);}
            //IF FILE IS NOT EMPTY: Read from File
            else {
                //first line: haveGAD haveDep
                try {
                    this.haveGAD = inScnr.nextInt() == 1;
                    this.haveDep = inScnr.nextInt() == 1;
                } catch (Exception ignored) {}

                //Add data to array
                int[] temp = new int[Main.NUM_QUESTIONS + 1]; //+1 because first value is date
                while (inScnr.hasNext()) {
                    for (int i = 0; i < Main.NUM_QUESTIONS + 1; i++) { //each row = 1 entry
                        try {
                            temp[i] = inScnr.nextInt(); //questions in the entry
                        } catch (Exception e) {
                            break; //if not enough values just go to the next row, it'll have 0s
                        }
                    }
                    ret.append(temp); //add temp to EntyList
                }

            }
        } catch(Exception e){
            System.out.println("ERROR: There's something wrong with the file scanner!");
            e.printStackTrace();
        }
        try { fileByteStream.close(); }
        catch (IOException e) {
            System.out.println("ERROR: There's something wrong with closing the fBS!");
            e.printStackTrace();
        }

        return ret;
    }

    //Angela: Prints array to file
    public int printF(EntryList el){
        Entry[] eArray = el.getMainArray();
        PrintWriter outFS = null;
        int ret = -1;

        try{
            FileOutputStream fileStream = new FileOutputStream(TEXT_FILE);
            outFS = new PrintWriter(fileStream);

            //Print personalization
            if(haveGAD){outFS.print("1 ");}else{outFS.print("0 ");}
            if(haveDep){outFS.print("1 ");}else{outFS.print("0 ");}
            outFS.println();

            //For each Entry, print out
            for(Entry e : eArray){
                outFS.print(e.print());
            }

            ret = 0;
        }
        catch(Exception ignored){}
        finally{
            outFS.close();
        }
        return ret;

    }

    //Get vars
    public boolean getGAD(){return haveGAD;}
    public boolean getDep(){return haveDep;}
}
