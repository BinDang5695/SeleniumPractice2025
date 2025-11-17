package test.baitap.javabasic;

public class Calculator {

    public int tong2SoNguyen(int a, int b)
    {
        int c = a + b;
        return c;
    }

    public double tich2SoThuc(double d, double e)
    {
        double f = d * e;
        return f;
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        System.out.println("Tong 2 so nguyen: " + calculator.tong2SoNguyen(4, 18));
        System.out.println("Tich 2 so thuc: " + calculator.tich2SoThuc(5.2, 8.7));

    }

}
