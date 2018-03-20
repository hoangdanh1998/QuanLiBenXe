package Container;

import java.util.Date;
import java.util.Scanner;

public class InputValid {

    public static int inputInteger(String notify) {
        Scanner input = new Scanner(System.in);
        int myNumber = 0;
        boolean isError;
        do {
            try {
                isError = false;
                System.out.print(notify);
                myNumber = Integer.parseInt(input.nextLine());
                if (myNumber < 0) {
                    throw new Exception("\u001B[31mYou must input a non-negative number\u001B[1m");
                }
            } catch (Exception e) {
                isError = true;
                System.out.println(e.getMessage() + "\u001B[31m! Please retype your number!\u001B[1m");
            }
        } while (isError == true);
        return myNumber;
    }
    
    public static boolean Confirm(){
        String myString;
        do{
            myString = inputString("Do you want to continue (Y/N)? _");
        } while (!myString.equalsIgnoreCase("y") && !myString.equalsIgnoreCase("n"));
        return myString.equalsIgnoreCase("y");
    }
    
    public static String inputString(String notify) {
        Scanner input = new Scanner(System.in);
        System.out.print(notify);
        return input.nextLine();
    }

    public static Date inputDate(String notify) {        
        Date theDate;
        do {
            try {
                String myDate = inputString(notify);
                String arr[] = myDate.split("[/]");
                if (arr.length == 3) {
                    int dd = Integer.parseInt(arr[0]);
                    int mm = Integer.parseInt(arr[1]);
                    int yyyy = Integer.parseInt(arr[2]);
                    theDate = new Date(yyyy, mm - 1, dd);
                    if (yyyy == theDate.getYear()
                            && mm == theDate.getMonth() + 1
                            && dd == theDate.getDate()) {
                        return theDate;
                    }
                    throw new Exception("Invalid date");
                    
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    public static Date StringToDate(String myString){ 
        if (myString.equals("")){
            return null;
        }
        String arr[] = myString.split("[/]");
                    int dd = Integer.parseInt(arr[0]);
                    int mm = Integer.parseInt(arr[1]);
                    int yyyy = Integer.parseInt(arr[2]);
                return new Date(yyyy, mm - 1, dd);
    }
    public static Date inputDateNotRequire(String notify) {
      //Input follow fomat dd/mm/yyyy    
        Date theDate;
        do {
            try {
                        String myDate = inputString(notify);
                        if (myDate.equals("")) return null;
                String arr[] = myDate.split("[/]");
                if (arr.length == 3) {
                    int dd = Integer.parseInt(arr[0]);
                    int mm = Integer.parseInt(arr[1]);
                    int yyyy = Integer.parseInt(arr[2]);
                    theDate = new Date(yyyy, mm - 1, dd);
                    if (yyyy == theDate.getYear()
                            && mm == theDate.getMonth() + 1
                            && dd == theDate.getDate()) {
                        return theDate;
                    }
                    throw new Exception("Invalid date");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }    
}
