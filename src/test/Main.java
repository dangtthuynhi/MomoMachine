package test;
import machine.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int opt;
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("1. Nạp tiền");
            System.out.println("2. Mua sản phẩm");
            //System.out.println("3. Xem thông tin");
            System.out.println("3. Lấy lại tiền thừa cùng sản phẩm");
            opt=sc.nextInt();
            switch (opt)
            {
                case 1:
                    Machine.insertMoney();
                    break;
                case 2:
                    Machine.purchase();
                    break;
                case 3:
                    Machine.cashInReturn();
                    break;
                default:
                    System.out.println("Nhập sai vui lòng nhập lại!");
                    break;
            }
        }while(true);
    }
}
