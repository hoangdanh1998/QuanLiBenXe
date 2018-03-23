
import Container.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
      
        Manager manager = new Manager();
        Menu menu = new Menu();
        menu.add("1 - Add a car.");
        menu.add("2 - Remove a car.");
        menu.add("3 - Modify a car.");
        menu.add("4 - Search car.");
        menu.add("5 - Sort list car and display it.");
        menu.add("6 - Display list car.");
        menu.add("7 - Read file store data.");
        menu.add("8 - Save data to file.");
        menu.add("9 - EXIT!");
        int choice; boolean isChanged = false;
        do {
            menu.print();
            do {
                choice = InputValid.inputInteger("Enter your choice: ");                
            } while (choice < 0 || choice > menu.size());
            switch (choice) {
                case 1:
                    manager.ChoiceAdd();     
                    isChanged = true;
                    break;
                case 2:
                    manager.ChoiceRemove();
                    isChanged = true;
                    break;
                case 3:
                    manager.ChoiceModify();
                    isChanged = true;
                    break;
                case 4:
                    manager.ChoiceSearchCar();
                    isChanged = true;
                    break;
                case 5:
                    manager.ChoiceSort();                    
                    break;
                case 6:
                    manager.display();
                    break;
                case 7:
                    manager.ChoiceReadFile(); 
                    break;
                case 8:                    
                    manager.ChoiceWriteFile("DATA.sak");
                    isChanged = false;
                    break;
                case 9:
                    if (isChanged){
                    if (!InputValid.Confirm("Save change to file (Y/N)?")){
                        System.out.println("Thank you");
                        System.exit(0);
                    } else manager.ChoiceWriteFile("DATA.sak");
                    } else System.out.println("Thank you.");
                    System.exit(0);
            }
        } while (true);

    
    }

}
