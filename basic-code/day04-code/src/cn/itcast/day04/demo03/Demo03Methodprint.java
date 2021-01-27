package cn.itcast.day04.demo03;
/*
用来打印指定次数的halloworld。
 */
public class Demo03Methodprint {

    public static void main(String[] args) {
        printCount(5);
    }

    /*
    返回值类型：只是进行打印操作，没有计算和结果
    方法名称：printCount
    参数列表：打印多少次？ 次数：int
     */
    public static void printCount(int num){
        for (int i = 0; i < num ; i++) {
            System.out.println("HalloWorld"+(i+1));
        }
    }
}
