import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class FileCallable implements Callable {

    public String name;
    public FileCallable(String name)
    {
        this.name=name;
    }

    public Object call() throws Exception
    {
        int numOfLines=0;
        String path=Ex2_1.getPath()+name+".txt"; //check if name of the file contains ".txt"

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
