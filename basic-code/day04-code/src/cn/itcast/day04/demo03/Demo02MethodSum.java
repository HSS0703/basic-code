package cn.itcast.day04.demo03;
/*
定义一个方法，用来求出1-100之前所有数字的和的值。
 */
public class Demo02MethodSum {

    public static void main(String[] args) {
        System.out.println("结果是" + getSum());
    }
    /*
    返回值类型：int
    方法名称：getSum
    参数列表：数据范围已经确定，是固定的，不需要告诉我任何条件，不需要参数。
     */
    public static int getSum(){
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i ;
        }
        return sum;
    }
}

