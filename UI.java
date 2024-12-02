import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class UI {
    Scanner scnr;
    int date;
    int feeling;
    String[] GAD = {"Feeling nervous, anxious, or on edge?",
                    "Not being able to stop or control worrying?",
                    "Worrying too much about different things?",
                    "Trouble relaxing?",
                    "Being so restless that it's hard to sit still?",
                    "Becoming easily annoyed or irritable?",
                    "Feeling afraid as if something awful might happen?"};
    /* https://www.mdcalc.com/calc/1727/gad7-general-anxiety-disorder7 */
    String[] Dep = {"Little interest or pleasure in doing things?",
                    "Feeling down, depressed, or hopeless?",
                    "Trouble falling or staying asleep, or sleeping too much?",
                    "Feeling tired or having little energy?",
                    "Poor appetite or overeating?",
                    "Feeling bad about yourself?",
                    "Trouble concentrating on things?",
                    "Noticeably more slow or restless than usual?"};
    /* https://www.mdcalc.com/calc/1725/phq9-patient-health-questionnaire9 */
    //Constructor
    public UI(){
        scnr = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("yyDDD");
        Date d = new Date();
        date = Integer.parseInt(formatter.format(d));
    }

    //Basically just scnr.nextInt() but easier
    public int askForInt(String question, int[] valids){ //ADD TRY/CATCH AND VALID VALUES
        System.out.println(question);
        int response;
        try {
            response = scnr.nextInt();
        }catch(Exception e){
            response = -1;
            scnr.next();
        }
        boolean isFound = response == valids[0];
        if(isFound){return response;}
        while(!isFound){ //loops until returns
            for(int i : valids){
                if(i == response){return response;} //breaks loop
            }
            System.out.println("Invalid! Please enter a number between " + valids[0] + "-" + valids[valids.length - 1] + ".");
            System.out.println(question);
            try {
                response = scnr.nextInt();
            }catch(Exception e){
                response = -1;
                scnr.next();
            }
        }
        return -1;
    }

    //Cindy: Ask questions, log answers
    public Entry takeSurvey(boolean haveGAD, boolean haveDep){
        int[] a = new int[Main.NUM_QUESTIONS];

        System.out.println("Mental Health Daily Check-in: \n");
        System.out.println("Mental Health Tracker");
        System.out.println("=====================");
        System.out.println("On a scale from 1 to 5, how are you feeling today?");
        System.out.println("1: Relaxed/Content\n2: Lonely/Insecure\n3: Productive/Motivated\n4: Tired/Drained\n5: Average/Fine\n6: Anxious/Frustrated");

        int[] ints16 = {1, 2, 3, 4, 5, 6}; //valid choices
        a[0] = askForInt("", ints16);
        feeling = a[0];

        int aindex = 1; //array index
        int[] ints01 = {0, 1}; //valid choices
        if(haveGAD){
            System.out.println("Today, have you been troubled by (0 for no, 1 for yes):");
            for(int i = 0; i < GAD.length; i++){
                a[aindex] = askForInt(GAD[i], ints01);
                aindex++;
            }
        }
        aindex = 1 + GAD.length;
        if(haveDep){
            System.out.println("Today, have you been troubled by (0 for no, 1 for yes):");
            for(int i = 0; i < Dep.length; i++){
                a[aindex] = askForInt(Dep[i], ints01);
                aindex++;
            }
        }
        return new Entry(date, a);
    }

    public int setUp(){
        System.out.println("Welcome! I see that this is your first time using this.");
        int[] ints = {1, 2, 3};
        return askForInt("Are you using this for anxiety (1), depression (2), or both? (3)", ints);
    }

    //Maxinne: Provide resources
    public void respond(){
        if (feeling == 1) {
            System.out.println("I'm so glad you're feeling relaxed and content! Enjoy this moment and remind yourself this peace and here are some suggestion to maintain this mood:");
            System.out.println("- Reflect and write in your journal of how your day has been.");
            System.out.println("- Take a quiet stroll outside and immerse yourself in nature.");
            System.out.println("- Share this positivity with others!");
            System.out.println("- Plan fun future events with family/or friends!");
            System.out.println("- Take a momment to compliment the achievements you've accomplished so far!");
        } else if (feeling == 2) {
            System.out.println("It seems like you're feeling lonely, I hope you're day get's better. In the mean time, here's some suggestions:");
            System.out.println("- Take a walk or run outside and get some fresh air.");
            System.out.println("- Take a cold shower and then eat your favorite dessert :) ");
            System.out.println("- Call a family or a friend for a chat.");
            System.out.println("- Listen to upbeat music");
            System.out.println("- WARMLINE: (833) 317-4673 Call the number if you'd like to talk to someone and discuss your current emotions.");

        } else if (feeling == 3) {
            System.out.println("It's great to hear that you're happy! Use this productive energy and complete tasks for the day. Here are some suggestions of activities to try out:");
            System.out.println("- Start an academic task that seems difficult and debunk them.");
            System.out.println("- Clean around the house or organize your items.");
            System.out.println("- Learn a new dish to cook or navigate cooking.");
            System.out.println("- Spread happiness to others by complimenting them!");
            System.out.println("- Encourage others to try out something new!");
        } else if (feeling == 4) {
            System.out.println("It's okay to feel drained. You've been working hard, take the time to listen to your body and get your rest. Here are some suggestion to improve your mood:");
            System.out.println("- Set a timer and scroll through social media.");
            System.out.println("- Select a song playlist that you enjoy!");
            System.out.println("- Connect with friends and/or love ones");
            System.out.println("- Listen to music or read a book");
            System.out.println("- Search for calming asmr audios");
        } else if (feeling == 5) {
            System.out.println("It's a good thing that you're feeling okay. It's important to celebrate little victories! Here are some suggestion to add a little joy to your day:");
            System.out.println("- Take good risks.");
            System.out.println("- Journal how your day been.");
            System.out.println("- Take the time to connect with friends and family.");
            System.out.println("- Try out a new game!");
            System.out.println("- Find a new hobby.");
        } else if (feeling == 6) {
            System.out.println("I understand that you're anxious, let's take a breather. Inhale for 4 seconds, hold your breath for 7 seconds, and exhale for 8 seconds. I hope that made you feel better. Try some more activities: ");
            System.out.println("- Find your happy place. Picture a place that makes you feel relaxed, ");
            System.out.println("- Close your eyes and slowly count to 10, repeat until you feel calm");
            System.out.println("- Sit down in a quiet place and recollect yourself. ");
            System.out.println("- Remind yourself that this is temporary, start with one task at a time");
            System.out.println("- National Suicide Prevention Lifeline: 988");
        } else {
            System.out.println("Invalid input. Please enter a number between 1 and 6.");
        }


        if (feeling >= 1 && feeling <= 6) {
            System.out.println("\nWhat has contributed to how you're feeling today?");
            scnr.nextLine();
            System.out.println("Thank you for sharing! Reflecting on your day can help you understand your mental health better.");
        }

    }

    //Provide data for the GAD and Dep parts
    public void diagnose(double[] avs){
        if(0.0 <= avs[0] && avs[0] < 5.0){System.out.println("No Anxiety Disorder.");}
        if(5.0 <= avs[0] && avs[0] < 8.0){System.out.println("Mild Anxiety Disorder.");}
        if(8.0 <= avs[0] && avs[0] < 14.0){System.out.println("Moderate Anxiety Disorder.");}
        if(14.0 <= avs[0] && avs[0] <= 21.0){System.out.println("Severe Anxiety Disorder.");}

        if(0.0 <= avs[1] && avs[1] < 5.0){System.out.println("No Depression.");}
        if(5.0 <= avs[1] && avs[1] < 10.0){System.out.println("Mild Depression.");}
        if(10.0 <= avs[1] && avs[1] < 15.0){System.out.println("Moderate Depression.");}
        if(15.0 <= avs[1] && avs[1] < 20.0){System.out.println("Moderately Severe Depression.");}
        if(20.0 <= avs[1] && avs[1] < 27.0){System.out.println("Severe Depression.");}

        System.out.println("NOTE: This is meant to be used alongside professional treatment!");
        System.out.println("This is just a CS project and not meant to actually diagnose/treat patients.");
    }

    //Get vars
    public int getDate(){ return date; }
}
