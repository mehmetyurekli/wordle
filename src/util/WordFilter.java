package util;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordFilter {

    public static void filter(String path){
        Set<String> set = new HashSet<>();
        PrintWriter outputStream = null;
        try{
            outputStream = new PrintWriter(new FileOutputStream("english.txt", true));
        }
        catch (Exception e){
            System.out.println("error");
            System.exit(0);
        }
        try {
            Scanner file = new Scanner(new FileInputStream(path));
            while(file.hasNext()){
                String line = file.nextLine().toLowerCase();
                line = line.replaceAll("\\-", "");
                line = line.replaceAll("\\.", "");
                line = line.replaceAll("\\*", "");
                line = line.replaceAll("\\'", "");
                line = line.replaceAll(" ", "").trim();
                line = line.replaceAll("â", "a");
                line = line.replaceAll("î", "i");
                line = line.replaceAll("û", "u");
                if(line.length() == 5){
                    set.add(line);
                }
            }
            file.close();
            PrintWriter finalOutputStream = outputStream;
            set.stream().forEach(str -> {
                try{
                    finalOutputStream.println(str);
                    finalOutputStream.flush();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            });
            finalOutputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Not found!");
        }


    }



}
