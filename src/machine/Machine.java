package machine;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Machine {
    static int balance = 0 //số tiền đã mua
            , total = 0//tổng tiền đã nạp
            , change=0//số dư
            , limitedBudget=50000;//hạn mức của chương trình khuyến mãi
    static double chance=0;//cơ hội trúng thưởng
    static double chancePlus=0;//cơ hội tăng thêm
    static HashMap<String,Integer> items= new HashMap<>();//Lưu tên sản phẩm cùng số lượng đã mua;
    static HashMap<Integer,Integer> number= new HashMap<>();//Lưu tiền thừa gồm mệnh giá + số lượng;
    static Scanner sc = new Scanner(System.in);
    static Integer[] money ={200000,100000,50000,20000,10000};
    static Date dateBefore=new Date();//lấy ngày khởi tạo machine
    public static void insertMoney() {//nạp tiền

        int opt;
        System.out.println("Chỉ chấp nhận 10.000\t20.000\t50.000\t100.000\t200.000");
        do {
            System.out.println("Số dư hiện tại: "+change);
            System.out.println("Nhập mệnh giá muốn nạp: (nhập 0 quay lại trang chủ)");
            opt = sc.nextInt();
            if(Arrays.asList(money).contains(opt))
            {
                changeWhenRecharge(opt);
            }
            else if(opt==0)
            {
                break;
            }
            else{
                System.out.println("Chỉ chấp nhận 10.000\t20.000\t50.000\t100.000\t200.000");}
        } while (opt != 0);
    }
    public static void changeWhenRecharge(int moneyAdd)//thay đổi khi nạp tiền
    {
        total+=moneyAdd;//cộng thêm tiền đã nạp
        change+=moneyAdd;//cộng thêm số dư
    }
    public static void changeOnPurchase(int price, String key)//thay đổi khi mua sản phẩm
    {
        //items.getOrDefault(key,0);
        balance+=price;//cộng thêm tiền hàng
        change-=price;//trừ đi số dư
        int quantity=items.getOrDefault(key,0);
        items.put(key,++quantity);//cộng thêm sản phẩm trong giỏ hàng

    }
    public static void purchase(){//mua sản phẩm
        int product;
        checkDate();
        do {
            System.out.println("Vui lòng chọn sản phẩm: ");
            System.out.println("1. Coke giá 10.000");
            System.out.println("2. Pepsi giá 10.000");
            System.out.println("3. Soda giá 20.000");
            System.out.println("0. Quay lại trang chủ");
            product=sc.nextInt();
            switch (product){
                case 1:
                    if(change>=Product.COKE.getPrice()) {//kiểm tra số dư có đủ không
                        changeOnPurchase(Product.COKE.getPrice(),Product.COKE.getName());
                        System.out.println("Bạn đã mua thành công sản phẩm");
                    }
                    else{
                        System.out.println("Vui lòng nạp thêm tiền!");
                        product=0;//Kết thúc việc mua sản phẩm
                    }
                    break;
                case 2:
                    if(change>=Product.PEPSI.getPrice()) {//kiểm tra số dư có đủ không
                        changeOnPurchase(Product.PEPSI.getPrice(),Product.PEPSI.getName());
                        System.out.println("Bạn đã mua thành công sản phẩm");

                    }
                    else{
                        System.out.print("Vui lòng nạp thêm tiền!");
                        product=0;
                    }
                    break;
                case 3:
                    if(change>=Product.SODA.getPrice()) {//kiểm tra số dư có đủ không
                        changeOnPurchase(Product.SODA.getPrice(),Product.SODA.getName());
                        System.out.println("Bạn đã mua thành công sản phẩm");

                    }
                    else{
                        System.out.print("Vui lòng nạp thêm tiền!");
                        product=0;
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nhập sai vui lòng nhập lại!");
                    break;
            }
        }while(product!=0);
    }
/*    public static void display()
    {
        System.out.println("Tổng tiền đã nạp: "+total);
        System.out.println("Đã mua: "+balance);
        System.out.println("Còn dư: "+change);
        //System.out.println("Phần trăm trúng thưởng: "+chance);
        System.out.println("Số lượng sản phẩm đã mua:");
        items.forEach((k,v)->System.out.println("\t"+k+": "+v));
    }*/
    public static void checkChance()//kiểm tra khi mua 3 sản phẩm giống nhau
    {
        chance+=chancePlus;
        for(String i:items.keySet())//lấy danh sách khóa
        {
            if(items.get(i)>=3)//nếu số lượng >=3
            {
                int temp=(int)items.get(i)/3;
                chance+=temp*0.1;
            }
        }
        if(chance>=1&&limitedBudget>=10000)
        {
            limitedBudget-=Product.PEPSI.getPrice();
            chance=0;
            System.out.println("Chúc mừng bạn đã được tặng thêm 1 chai Pepsi");
        }
    }
    public static void checkDate()//kiểm tra ngày có đủ 50k khuyến mãi không
    {
        Date dateAfter=new Date();//lấy ngày hiện tại
        int comparison=dateAfter.compareTo(dateBefore);//so sánh ngày khởi tạo machine với ngày hiện tại
        if(comparison > 0)//nếu khác nhau
        {
            dateBefore=dateAfter;
            if(limitedBudget>0)//kiểm tra xem ngân sách 50k có dư hay không?
            {
                chancePlus=0.5;//tăng thêm 50%
            }
            else chancePlus=0;
            limitedBudget=50000;//đặt lại giá trị ngân sách 50k cho ngày hôm nay.
        }
    }

    public static void cashInReturn()//thối tiền
    {
        checkChance();
        System.out.println("Tổng tiền đã nạp: "+total);
        System.out.println("Đã mua: "+balance);
        System.out.println("Còn dư: "+change);
        if(change>0)//đổi ra tiền
        {
            for(int i:money)
            {
                while(change>=i)
                {
                    int quantity=number.getOrDefault(i,0);
                    number.put(i,++quantity);
                    change-=i;
                }
            }
        }
        if(!number.isEmpty())
        {
            System.out.println("Tiền thừa được gửi lại cho quý khách như sau: ");
            number.forEach((k,v)->System.out.println("\tTờ "+k+": "+v+" tờ"));
        }
        if(!items.isEmpty()) {
            System.out.println("Số lượng sản phẩm đã mua:");
            items.forEach((k, v) -> System.out.println("\t" + k + ": " + v));
        }
        System.out.println("Hẹn gặp lại quý khách!");
        balance=0;
        total=0;
        chance=0;
        number.clear();
        items.clear();

    }
}