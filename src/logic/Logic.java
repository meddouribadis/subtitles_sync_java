package logic;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.*;
import java.util.ArrayList;

public class Logic {

    private Pattern pattern;
    private Matcher matcher;
    private String regex = "[0-9]:[0-9][0-9]:[0-9][0-9].[0-9][0-9]";
    private String subRegex = "[0-9][0-9]";
    private String filename;
    private String textInput;
    private ArrayList<String> matches;
    private String currentPath;

    public Logic(){
        this.currentPath = System.getProperty("user.dir")+  "\\subtitles\\";
        this.pattern = Pattern.compile(regex, Pattern.MULTILINE);
        this.matches = new ArrayList<>();
        this.textInput = "";
    }

    public void matchTime(String filenameInput, int delay) throws Exception {
        this.filename = filenameInput;
        System.out.println(this.currentPath);

        File file = new File(this.currentPath+"\\"+this.filename);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            textInput += st+"\n";

        System.out.println(textInput);
        this.matcher = pattern.matcher(textInput);


        while (matcher.find()) {
            System.out.println("Full match: " + matcher.group(0));
            this.matches.add(matcher.group(0));
            for (int i = 1; i <= matcher.groupCount(); i++) {
            }
        }

        System.out.println("Subtitles detected : "+this.matches.size());

        this.matches.forEach((element) -> {
            try {
                subElement(element, delay);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        System.out.println(textInput);
        saveFile();
    }

    private void subElement(String element, int delay) throws ParseException {
        System.out.println(element);
        this.pattern = Pattern.compile(subRegex, Pattern.MULTILINE);
        this.matcher = pattern.matcher(element);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss:SS");
        ArrayList<String> timeData = new ArrayList<>();
        while (matcher.find()) {
            timeData.add(matcher.group(0));
        }

        Date c= sdf.parse("26-05-2010-12:"+timeData.get(0)+":"+timeData.get(1)+":"+timeData.get(2));
        //c.setSeconds(c.getSeconds() - 1);
        c = addMilliseconds(c, -(delay));
        String date=sdf.format(c);
        String replacement = "0:"+c.getMinutes()+':'+c.getSeconds()+'.'+timeData.get(2);

        /*
        Calendar newYearsEve = Calendar.getInstance();
        newYearsEve.set(2012, 5, 10, 12, Integer.parseInt(timeData.get(0)), Integer.parseInt(timeData.get(1)));
        Calendar newYearsDay = Calendar.getInstance();
        newYearsDay.setTimeInMillis(newYearsEve.getTimeInMillis());
        newYearsDay.add(Calendar.MILLISECOND, Integer.parseInt(timeData.get(2)));
        newYearsDay.add(Calendar.MILLISECOND, -1100);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss:S");
        String replacement = "0:"+newYearsDay.get(Calendar.MINUTE)+':'+newYearsDay.get(Calendar.SECOND)+'.'+newYearsDay.get(Calendar.MILLISECOND);
        */
        System.out.println("New time = " + replacement);
        textInput = textInput.replace(element, replacement);
    }

    private void saveFile() throws IOException {
        FileWriter myWriter = new FileWriter(currentPath+"\\output\\"+filename);
        myWriter.write(textInput);
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }

    public static Date addMilliseconds(Date date, int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }

    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }
}
