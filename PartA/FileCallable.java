package PartA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;


/**
 * This class implements Callable Interface and use it to calculate number of lines in a specific file.
 */
public class FileCallable implements Callable
{
    public String name;

    /**
     * This is a constructor that gets a name of a file and initialize it.
     * @param name
     */
    public FileCallable(String name)
    {
        this.name=name;
    }

    /**
     * This method calculate number of lines in a specific file.
     * @return Object type that contains the number of lines.
     * @throws Exception
     */
    public Object call() throws Exception
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

        return numOfLines;
    }
}
