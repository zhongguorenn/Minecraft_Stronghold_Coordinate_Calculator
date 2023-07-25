/**
 * Minecraft Stronghold Coordinate Calculator
 * GitHub user_name zhongguorenn
 * It has changed several editions, and this version should be possible
 * Note: The y value in this program is the y value in the function, not the y value in the game
 * Project address https://github.com/zhongguorenn/Minecraft_Stronghold_Coordinate_Calculator
 * Translated by zhongguorenn and Microsoft Translator
 * Proofread by zhongguorenn and Baidu Translator
 * @author zhongguorenn
 * @version 1.0.1
 */

import java.util.Scanner;

public class Minecraft_Stronghold_Coordinate_Calculator_enUS {
    public static void main(String[] args) {
        System.out.println("******Minecraft_Stronghold_Coordinate_Calculator******");
        System.out.println("This program is made by bilibili种果仁丶and is open sourced on GitHub");
        System.out.println("If you paid for a purchase, request a refund immediately and accusation the merchant");
        System.out.println("The two Ender Eyes throw points, please do not get too close");
        System.out.println("If you encounter any bugs, please report them https://github.com/zhongguorenn/Minecraft_Stronghold_Coordinate_Calculator");
        System.out.println("-------------Dividing line---------------");
        boolean wheelKValue=false;
        boolean leftToRight=false;
        //-----------------------Dividing line-------------------------------
        //input module, to obtain the necessary four-point coordinates
        Scanner s =new Scanner(System.in);
        System.out.println("The first starting point x coordinate");
        double Sx1= s.nextDouble();
        System.out.println("The first starting point z-coordinate");
        double Sy1= s.nextDouble();
        System.out.println("The first drop point x coordinates");
        double Ex1= s.nextDouble();
        System.out.println("The first drop point z-coordinate");
        double Ey1= s.nextDouble();
        System.out.println("The second starting point x coordinate");
        double Sx2= s.nextDouble();
        System.out.println("The second starting point z-coordinate");
        double Sy2= s.nextDouble();
        System.out.println("The second drop point x coordinate");
        double Ex2= s.nextDouble();
        System.out.println("The second drop point z-coordinate");
        System.out.println("Whether to enable acceleration algorithms");
        String YN = s.nextLine();
        boolean enable;
        enable= YN.equals("y");
        double Ey2= s.nextDouble();
        double k1 = (Ey1-Sy1)/(Ex1-Sx1);
        double k2 = (Ey2-Sy2)/(Ex2-Sx2);
        double b1 = (Sy1-Sx1*k1);
        double b2 = (Sy2-Sx2*k2);
        //-----------------------Dividing line-------------------------------
        //Avoid doing meaningless calculations
        if(k1==k2){
            System.out.println("Error: Slopes cannot be equal");
            System.exit(1);
        }
        //-----------------------Dividing line-------------------------------
        //Output function expressions to prevent calculation failure
        System.out.println("Function 1 is:y1="+k1+"*x+"+b1);
        System.out.println("Function 2 is:y2="+k2+"*x+"+b2);
        System.out.println("If the calculation fails, please import this function into the function drawing program to find the intersection point, such as the calculator that comes with Win10 and above");
        //-----------------------Dividing line-------------------------------
        // Inverse function algorithm acceleration module
        // whenever a small x change causes a large change in the y value
        if((((k1>0&k1<1)&(k2>0&k2<1))|((k1<0&k1>-1)&(k2<0&k2>-1))|((k1>0&k1<1)&(k2<0&k2>-1))|(k1<0&k1>-1)&(k2>0&k2<1))&enable){
            wheelKValue=true;
            k1=(1/k1);
            k2=(1/k2);
            b1=(Sx1-k1*Sy1);
            b2=(Sx2-k2*Sy2);
        }//Algorithm initialization ends
        //-----------------------Dividing line-------------------------------
        //Function y-axis symmetry algorithm acceleration module
        // Symmetry the intersection located on the negative semi-axis to the positive semi-axis to reduce traversal time
        if((Math.abs((k1+b1-k2-b2))>Math.abs((-k1+b1+k2-b2)))&enable){
            leftToRight=true;
            k1=-k1;
            k2=-k2;
        }//Algorithm initialization ends

        //-----------------------Dividing line-------------------------------
        //Reduce traversal range
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
        //Compute module (acceleration algorithms eliminate classification discussions)
        for (double x = tmp;x<(int)(Math.pow(2,31)-1);x+=0.01){
            if(-0.01<=((k1-k2)*x+b1-b2)&((k1-k2)*x+b1-b2)<=0.01) {
                double  y=(k1 * x + b1);
                if (leftToRight) {
                    //Symmetric algorithm restoration
                    x = -x;
                    if (wheelKValue) {
                        //Inverse function algorithm restore
                        double Tmp = x;
                        x = y;
                        y = Tmp;
                    }
                }
                x = (int) x;
                y = (int) y;
                System.out.println("X-coordinate:" + x);
                System.out.println("Z coordinate:" + y);
                System.out.println("teleport command:/tp " + x + " ~"+ " " + y );
                break;
            }
        }
        //-----------------------Dividing line-------------------------------
        //exit
        System.out.println("The program is about to exit after execution");
        System.exit(0);
    }
}
