package main;

import logic.Logic;
import java.io.IOException;

public class Main {
    public static void main (String[] args){

        for(int i = Integer.parseInt(args[0]); i <= Integer.parseInt(args[1]); i++){
            Logic logical = new Logic();
            try {
                if(i < 10){
                    logical.matchTime("00"+i+".ass", Integer.parseInt(args[2]));
                }
                else if(i < 100){
                    logical.matchTime("0"+i+".ass", Integer.parseInt(args[2]));
                }
                else if(i >= 100){
                    logical.matchTime(i+".ass", Integer.parseInt(args[2]));
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

    }
}
