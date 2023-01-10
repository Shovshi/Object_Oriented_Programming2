package PartA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileThread extends Thread{

    public String name;
    public int numLines=0; //we create variable to hold the num of lines of each thread (file)
    public FileThread(String name)
    {
        this.name=name;
    }

    public void run()
    {
        int numOfLines=0;
        String path=name+".txt"; //check if name of the file contains ".txt"
        try
        {
            FileReader fr= new FileReader(path);
            BufferedReader br=new BufferedReader(fr);
            String line ="";
            while(line!=null)
            {
                line= br.readLine();
                numOfLines++;
            }
            numOfLines--; //in the end of the file it reads null context
            br.close();
            fr.close();
        }
        catch(IOException e)
        {
            System.err.println("Error");
        }

        numLines =  numOfLines;
    }

    public int getLines()
    {
        return numLines;
    }

}
