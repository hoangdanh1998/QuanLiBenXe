
import Container.*;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        Manager manager = new Manager();
        int choice;
        do {
            Menu.MyMenu();
            do {
                choice = InputValid.inputInteger("Enter your choice: ");
            } while (choice < 0 || choice > 9);
            switch (choice) {
                case 1:
                    manager.ChoiceAdd();
                    break;
                case 2:
                    manager.ChoiceRemove();
                    break;
                case 3:
                    manager.ChoiceModify();
                    break;
                case 4:
                    manager.ChoiceSearchCar();
                    break;
                case 5:
                    manager.ChoiceSort();
                    break;
                case 6:
                    manager.display();
                    break;
                case 7:
                    manager.ChoiceReadFile("DATA.sak");
                    break;
                case 8:
                    manager.ChoiceWriteFile("DATA.sak");
                    break;                    
                case 9:    
                    System.exit(0);
            }
        } while (true);

    
    }
    
}
