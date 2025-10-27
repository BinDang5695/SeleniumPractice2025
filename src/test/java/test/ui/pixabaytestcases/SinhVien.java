package test.ui.pixabaytestcases;

public class SinhVien {

    public String name = "Bin";
    public static int age = 18;

    public String city()
    {
        return "DN";
    }

    public static void main(String[] args) {

        SinhVien sinhVien = new SinhVien();

        System.out.println("Name: " + sinhVien.name);

        System.out.println("Age: " + age);

        System.out.println("Address: " + sinhVien.city());

        ThongTin thongTin = new ThongTin();

        System.out.println("Country: " + thongTin.country);

        System.out.println("Job: " + thongTin.job);

        System.out.println("Gender is men: " + thongTin.ismen);

    }


}
