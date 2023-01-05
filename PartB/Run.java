package PartB;

public class Run
{
    public static void main(String[] args)
    {
        Task<Integer> obj = new Task<Integer>();


        int result = obj.Test((Integer x) -> x + 1, 5);
        System.out.println(result);





        Task<Integer> t1 = new Task<Integer>();
        int res = t1.Test((Integer x) -> {
            for (int i = 0; i < 10 ; i++) {
                System.out.println("this is x:" + x);
                x = x + i;
            }

            return x;
        },5);

        System.out.println(res);
    }
}
