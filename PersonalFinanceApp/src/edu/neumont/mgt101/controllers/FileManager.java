package edu.neumont.mgt101.controllers;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
public class FileManager {

    public static void writeData(String path, String data){
        File file = new File(path);
        if (Files.exists(Paths.get(path)) == false){
            //System.out.println("Login data has been found successfully.");
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            FileWriter fw = new FileWriter(path, true);
            fw.write("\n");
            fw.write(data);
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static String readFile(String path){
        File file = new File(path);
        StringBuilder content = new StringBuilder();
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                content.append(line);
                System.out.println(line);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return content.toString();
    }
}
