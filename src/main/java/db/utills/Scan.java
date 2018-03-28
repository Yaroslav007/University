package db.utills;

public class Scan {

    public static int scannerInt(String message){
        System.out.println(message);
        java.util.Scanner sc = new java.util.Scanner(System.in);
        return sc.nextInt();
    }

    public static String scannerString(String message){
        System.out.println(message);
        java.util.Scanner sc = new java.util.Scanner(System.in);
        return sc.nextLine();
    }
}
