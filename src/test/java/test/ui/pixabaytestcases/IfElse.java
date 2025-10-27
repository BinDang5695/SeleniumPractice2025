package test.ui.pixabaytestcases;

public class IfElse {

    public static void main(String[] args) {

        int a = 10;
        int b = 20;
        int c = a + b;

        System.out.println((a < b) && (b < c));
        System.out.println((a > b) || (b > c));
        System.out.println((a == b) && (b == c));

        IfElse.ifElse(200);

    }

    public static void ifElse(int n)
    {
        int number = 100;

        if(n == number){
            System.out.println("N = number <=> " + n + " = " + number );
    } else if (n < number) {
            System.out.println("N < number <=> " + n + " < " + number);
        } else {
            System.out.println("N > number <=> " + n + " > " + number);
        }
    }

}
