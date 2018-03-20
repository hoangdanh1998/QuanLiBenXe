package Container;

import java.util.Vector;

public class Menu extends Vector<String>{
    
    public void print(){
        System.out.println();
        System.out.println("\u001B[35m#########################| LIST CARS MANANGER |########################\u001B[1m");
        System.out.println("Functions to manage list cars:");
        for (String myString : this){
            System.out.println(myString);
        }
    }

    static class Notification {

        static void showError(String notify) {
            System.out.println("\t\t\u001B[31m" + notify + "\u001B[1m");
        }
        static  void showNotContain(String notify){
            System.out.println("\t\u001B[31m Park does not contain car "+ notify+"\u001B[1m");
        }
        static void HeadTable() {
            System.out.println("LIST OF CAR");
            System.out.printf("Form:< %10s | %20s | %15s | %15s | %10s | %10s >\n", "CODE", "Car's Name", "OWNER", "MANUFACTURE", "DATE IN", "DATE OUT");
            System.out.println("--------------------------------------------------------------------------------------------------------");
        }

        static void Success(String action) {
            System.out.println("\u001B[32m" + action + " successfully!\u001B[0m");
        }

        static void SearchCarMenu() {
            System.out.println("    | SEARCH CAR IN LIST");
            System.out.println("    |");
            System.out.println("    | 1. Search by CODE.");
            System.out.println("    | 2. Search by NAME.");
            System.out.println("    | 3. Search by OWNER.");
            System.out.println("    | 4. Search by MANUFACTURE.");
            System.out.println("    | 5. Search by DATE IN.");
            System.out.println("    | 6. Search by DATE OUT.");
            System.out.println("    | 7. Quit search.");
            System.out.println("    |");
        }

        static void SortListFirstTable() {
            System.out.println("    | SORT LIST CAR");
            System.out.println("    |");
            System.out.println("    | 1. Sort by CODE.");
            System.out.println("    | 2. Sort by NAME.");
            System.out.println("    | 3. Sort by OWNER.");
            System.out.println("    | 4. Sort by MANUFACTURE.");
            System.out.println("    | 5. Sort by DATE IN.");
            System.out.println("    | 6. Sort by DATE OUT.");
            System.out.println("    | 7. Quit Sort.");
            System.out.println("    |");
        }
        static void SortListSecondTable() {
            System.out.println("    |");
            System.out.println("    | TYPE SORT");
            System.out.println("    | 1. Ascending");
            System.out.println("    | 2. Descending");
            System.out.println("    |");
        }

        static void Header(String Operator) {
            System.out.println("\u001B[34m======== " + Operator + " ========\u001B[0m");
        }

    }
}
