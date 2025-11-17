package test.baitap.javabasic;

public class ToanTu {

    public static void main(String[] args) {

        int a = 10;
        int b = 20;
        int c = 30;

        System.out.println((a < b) && (b < c));
        System.out.println((a > b) || (b > c));
        System.out.println((a == b) || (b == c));

        ifElse(200);

    }

    public static void ifElse(int n)
    {
        int number = 100;

        if(n == number){
            System.out.println("n = number <=> " + n + " = " + number);
        } else if (n < number) {
            System.out.println("n < number <=> " + n + " < " + number);
        } else{
            System.out.println("n > number <=> " + n + " > " + number);
        }

    }
}
