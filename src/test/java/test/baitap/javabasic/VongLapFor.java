package test.baitap.javabasic;

public class VongLapFor {

    public static void main(String[] args) {

        int mang[] = new int[26];
        int index = 0;

        for (int i = 0; i <= 50; i++){
            if(i % 2 == 0){
                System.out.println("So chan: " + i);
            }
            mang[index] = i;
            index++;
        }

        for(int value : mang){
            System.out.println(value);
        }


    }

}
