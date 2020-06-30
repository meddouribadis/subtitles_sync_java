package main;

import logic.Logic;
import java.io.IOException;

public class Main {
    public static void main (String[] args){

        for( int i = 1; i < 5; i++){
            Logic salut = new Logic();
            try {
                if(i < 10){
                    salut.matchTime("00"+i+".ass");
                }
                else {
                    salut.matchTime("0"+i+".ass");
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
