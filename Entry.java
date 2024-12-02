public class Entry {
    int date;
    int[] values;
    int numQuestions;
    int[] txt;

    //Constructors
    public Entry(){}
    public Entry(int d, int[] a) {
        this.date = d;
        this.numQuestions = a.length;
        this.values = a;
    }
    public Entry(int[] da){
        this.date = da[0];
        this.numQuestions = da.length - 1;
        this.values = new int[numQuestions];
        for(int i = 1; i < da.length; i++) {
            this.values[i-1] = da[i];
        }
        this.txt = da;
    }

    //Testing
    public String print(){
        String ret = date + " ";
        for(int i : values){
            ret += i + " ";
        }
        ret += "\n";
        return ret;
    }

    //Get vars
    public int[] getArray(){
        if(this.txt == null){
            this.txt = new int[numQuestions + 1];
            this.txt[0] = date;
            for(int i = 1; i < numQuestions + 1; i++){
                this.txt[i] = values[i-1];
            }
        }
        return this.txt;
    }

}
