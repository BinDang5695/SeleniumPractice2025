package test.ui.pixabaytestcases;

public class Calculator {

    public int tong2SoNguyen(int a, int b)
    {
        int c = a + b;
        return c;
    }

    public double tong2SoThuc(double c, double d)
    {
        double e = c + d;
        return e;
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        System.out.println("Tong 2 so nguyen a va b = " + calculator.tong2SoNguyen(4, 6));

        System.out.println("Tong 2 so thuc c va d = " + calculator.tong2SoThuc(5.5, 9.1));

    }

}
