package cn.itcast.day04.demo02;

public class EX{
    public static void main(String[] args){
        int a = 10 ; //实参a，局部变量
        int b = 20 ;//实参b，局部变量

        EX num = new EX();
        num.swap(a,b);

        System.out.println(a);
        System.out.println(b);

        }
    public void swap(int A,int B){
            int temp ; //局部变量
            temp = A;
            A=B;
            B=temp;
    }
}

