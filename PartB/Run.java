package PartB;

import java.util.concurrent.Callable;

public class Run
{
    public static void main(String[] args) throws Exception {

        Callable t1 = Task.createTask(() -> {
            int x = 0;
            for (int i = 0; i < 10 ; i++) {
                System.out.println("this is x:" + x);
                x = x + i;
            }

            return x;});


        System.out.println(t1.call());

        Callable t2 = Task.createTask(() ->
        {
            double average = 0;
            for (int i = 0; i < 10; i++) {
                average+= i;
            }
            return average/10;
        });

        System.out.println(t2.call());

    }
}

