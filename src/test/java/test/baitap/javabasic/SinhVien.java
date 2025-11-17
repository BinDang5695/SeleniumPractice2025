package test.baitap.javabasic;


public class SinhVien {

    public String name = "Bin";
    public static int age = 18;

    public void bienLocal()
    {
        String address = "BTX";
        System.out.println("Address: " + address);
    }

    public static void main(String[] args) {

        SinhVien sinhVien = new SinhVien();
        System.out.println("Name: " + sinhVien.name);
        System.out.println("Age: " + age);
        sinhVien.bienLocal();

        System.out.println("City: " + ThongTin.city);
        System.out.println("Job: " + ThongTin.job);
        System.out.println("Gender is men: " + ThongTin.ismen);

    }

}
