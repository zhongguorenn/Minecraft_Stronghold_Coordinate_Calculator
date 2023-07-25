/**
 * 我的世界末影要塞坐标计算器最终版
 * by bilibili种果仁丶
 * GitHub昵称zhongguorenn
 * 改了好几版这版应该能成
 * 注：本程序中的y为函数中的y而不是游戏中的y
 * 项目地址https://github.com/zhongguorenn/Minecraft_Stronghold_Coordinate_Calculator
 * @author zhongguorenn
 * @version 1.0.1
 */

import java.util.Scanner;

public class Minecraft_Stronghold_Coordinate_Calculator_zhCN {
    public static void main(String[] args) {
        System.out.println("******我的世界末影要塞坐标计算器******");
        System.out.println("本程序由bilibili种果仁丶制作，且已在GitHub上开源");
        System.out.println("如果你是付费购买，请立刻申请退款，并举报商家");
        System.out.println("两个抛出点请不要离得太近");
        System.out.println("如果遇到什么bug请来https://github.com/zhongguorenn/Minecraft_Stronghold_Coordinate_Calculator");
        System.out.println("-------------分割线---------------");
        boolean wheelKValue=false;
        boolean leftToRight=false;
        //-----------------------分割线-------------------------------
        //输入模块，获取必要的四点坐标
        Scanner s =new Scanner(System.in);
        System.out.println("第一个起点x坐标");
        double Sx1= s.nextDouble();
        System.out.println("第一个起点z坐标");
        double Sy1= s.nextDouble();
        System.out.println("第一个落点x坐标");
        double Ex1= s.nextDouble();
        System.out.println("第一个落点z坐标");
        double Ey1= s.nextDouble();
        System.out.println("第二个起点x坐标");
        double Sx2= s.nextDouble();
        System.out.println("第二个起点z坐标");
        double Sy2= s.nextDouble();
        System.out.println("第二个落点x坐标");
        double Ex2= s.nextDouble();
        System.out.println("第二个落点z坐标");
        double Ey2= s.nextDouble();
        System.out.println("是否启用加速算法");
        String YN = s.nextLine();
        boolean enable;
        enable= YN.equals("y");
        double k1 = (Ey1-Sy1)/(Ex1-Sx1);
        double k2 = (Ey2-Sy2)/(Ex2-Sx2);
        double b1 = (Sy1-Sx1*k1);
        double b2 = (Sy2-Sx2*k2);
        //-----------------------分割线-------------------------------
        //避免做无意义的计算
        if(k1==k2){
            System.out.println("错误:斜率不能相等");
            System.exit(1);
        }
        //-----------------------分割线-------------------------------
        //输出函数表达式以防计算失败
        System.out.println("函数1为:y1="+k1+"*x+"+b1);
        System.out.println("函数2为:y2="+k2+"*x+"+b2);
        System.out.println("若计算失败请将此函数导入函数绘制程序求交点如win10及以上自带的计算器");
        //-----------------------分割线-------------------------------
        // 反函数算法加速模块
        //使无论何时较小的x变化能引起较大的y值变化
        if((((k1>0&k1<1)&(k2>0&k2<1))|((k1<0&k1>-1)&(k2<0&k2>-1))|((k1>0&k1<1)&(k2<0&k2>-1))|(k1<0&k1>-1)&(k2>0&k2<1))&enable){
            wheelKValue=true;
            k1=(1/k1);
            k2=(1/k2);
            b1=(Sx1-k1*Sy1);
            b2=(Sx2-k2*Sy2);
        }//算法初始化结束
        //-----------------------分割线-------------------------------
        //函数y轴对称算法加速模块
        // 将位于负半轴的交点对称到正半轴,以减少遍历时间
        if((Math.abs((k1+b1-k2-b2))>Math.abs((-k1+b1+k2-b2)))&enable){
            leftToRight=true;
            k1=-k1;
            k2=-k2;
        }//算法初始化结束

        //-----------------------分割线-------------------------------
        //减少遍历范围
        double tmp;
        if(wheelKValue){
            tmp = Math.min(Sy1, Sy2);
        }
        else {
            if(leftToRight) {
                tmp = Math.min(-Sx1,-Sx2);
            }
            else {
                tmp = Math.min(Sx1,Sx2);
            }
        }
        //计算模块(加速算法免去了分类讨论)
        for (double x = tmp;x<(int)(Math.pow(2,31)-1);x+=0.01){
            if(-0.01<=((k1-k2)*x+b1-b2)&((k1-k2)*x+b1-b2)<=0.01) {
                double  y=(k1 * x + b1);
                if (leftToRight) {
                    //对称算法还原
                    x = -x;
                    if (wheelKValue) {
                        //反函数算法还原
                        double Tmp = x;
                        x = y;
                        y = Tmp;
                    }
                }
                x = (int) x;
                y = (int) y;
                System.out.println("x坐标:" + x);
                System.out.println("z坐标:" + y);
                System.out.println("传送命令:/tp " + x + " ~"+ " " + y );
                break;
            }
        }
        //-----------------------分割线-------------------------------
        //退出程序
        System.out.println("执行完毕即将退出程序");
        System.exit(0);
    }
}
