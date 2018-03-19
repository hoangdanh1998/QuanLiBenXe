package Container;

import Model.Car;
import java.io.*;
import java.util.Date;

public class Manager {

    static CarList data = new CarList(1000);

    public void display() {
        data.display();
    }

    static String inputCode(String notify) {
        String code;
        boolean isError;
        do {
            isError = false;
            code = InputValid.inputString(notify);

            if (code.equals("")) {
                isError = true;
                Menu.Notification.showError("You must fill CODE: ");
            } else if (data.isExistCarCode(code)) {
                Menu.Notification.showError("This code EXISTED.");
                isError = true;
            }
        } while (isError);
        return code;
    }

    static Date inputDateOut(Date dateIn) {
        Date dateOut;
        do {
            dateOut = InputValid.inputDateNotRequire("\tEnter Date OUT [dd/mm/yyyy]: ");
            if (dateOut == null) {
                return null;
            } else if (dateOut.before(dateIn)) {
                Menu.Notification.showError("Day in must be after day out.");
            }
        } while (dateOut.before(dateIn));
        return dateOut;
    }

    public void ChoiceReadFile(String fileName) {

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String code;
            while ((code = br.readLine()) != null) {
                String name = br.readLine();
                String owner = br.readLine();
                String manufacture = br.readLine();
                Date DateIn = InputValid.StringToDate(br.readLine());
                Date DateOut = InputValid.StringToDate(br.readLine());
                if (!data.isExistCarCode(code)) {
                    data.add(new Car(code, name, owner, manufacture, DateIn, DateOut));
                }
            }
        } catch (Exception ex) {
        }
        Menu.Notification.Success("Read file");
    }

    public void ChoiceWriteFile(String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"))) {
            for (Car thisCar : data) {
                writer.write(thisCar.getCode());
                writer.newLine();
                writer.write(thisCar.getName());
                writer.newLine();
                writer.write(thisCar.getOwner());
                writer.newLine();
                writer.write(thisCar.getManufacture());
                writer.newLine();
                writer.write(thisCar.dateToString(thisCar.getDateIn()));
                writer.newLine();
                writer.write(thisCar.dateToString(thisCar.getDateOut()));
                writer.newLine();
            }
            writer.close();
        }

    }

    public void ChoiceAdd() {
        Menu.Notification.Header("ADD CAR");
        System.out.println("This is REQUIRED FIELDS: ");
        String code = inputCode("\tEnter car's CODE: ");
        Date dateIn = InputValid.inputDate("\tEnter Date In [dd/mm/yyyy]: ");
        System.out.println("This is NOT required fields, enter if you unknown: ");
        Date dateOut = inputDateOut(dateIn);
        String name = InputValid.inputString("\tEnter car's NAME : ");
        String owner = InputValid.inputString("\tEnter car's OWNER: ");
        String manufacture = InputValid.inputString("\tEnter car's MANUFACTURE: ");
        data.addCar(new Car(code, owner, name, manufacture, dateIn, dateOut));
        Menu.Notification.Success("Add");
    }

    public void ChoiceRemove() {
        String code;
        Menu.Notification.Header("REMOVE CAR");
        if (data.getSize() == 0) {
            System.out.println("This list is EMPTY can't remove");
            return;
        }
        do {
            code = InputValid.inputString("Enter Car's Code:");
            if (!data.isExistCarCode(code)) {
                Menu.Notification.showNotContain(code);
                if (!InputValid.Confirm()) {
                    return;
                }
            } else {
                break;
            }
        } while (true);
        data.removeCar(code);
        Menu.Notification.Success("Remove");
    }

    public void ChoiceModify() {
        String code;
        Menu.Notification.Header("Modify CAR");
        if (data.getSize() == 0) {
            System.out.println("This list is EMPTY can't modify");
            return;
        }
        do {
            code = InputValid.inputString("Enter Car's Code:");
            if (!data.isExistCarCode(code)) {
                Menu.Notification.showNotContain(code);
                if (!InputValid.Confirm()) {
                    return;
                }
            } else {
                break;
            }
        } while (true);
        System.out.println("Start Modify:");

        System.out.println("This is NOT required fields, enter if don't modify: ");
        Date dateIn = InputValid.inputDateNotRequire("\tEnter Date In [dd/mm/yyyy]: ");
        dateIn = (dateIn == null) ? data.searchByCode(code).getDateIn() : dateIn; // It's require so we shouldn't to check NULL

        Date dateOut = inputDateOut(dateIn);
        dateOut = (dateOut == null) ? data.searchByCode(code).getDateOut() : dateOut;
        if (dateOut != null) {
            dateOut = (dateOut.before(dateIn)) ? null : dateOut;
        }
        String name = InputValid.inputString("\tEnter car's NAME : ");
        name = (name.equals("")) ? data.searchByCode(code).getName() : name;

        String owner = InputValid.inputString("\tEnter car's OWNER: ");
        owner = (owner.equals("")) ? data.searchByCode(code).getOwner() : owner;

        String manufacture = InputValid.inputString("\tEnter car's MANUFACTURE: ");
        manufacture = (manufacture.equals("")) ? data.searchByCode(code).getManufacture() : manufacture;

        data.modifyCar(new Car(code, owner, name, manufacture, dateIn, dateOut));
        Menu.Notification.Success("Modify");
    }

    public void ChoiceSearchCar() {
        Menu.Notification.Header("Search CAR");

        int choice;
        if (data.getSize() == 0) {
            System.out.println("This list is EMPTY can't search");
            return;
        }
        do {
            Menu.Notification.SearchCarMenu();
            do {
                choice = InputValid.inputInteger("Which choice you want:");
            } while (choice < 1 || choice > 7);
            switch (choice) {
                case 1:
                    String code;
                    do {
                        code = InputValid.inputString("\t\tEnter CODE: ");
                        if (!data.isExistCarCode(code)) {
                            Menu.Notification.showNotContain(code);
                            if (!InputValid.Confirm()) {
                                return;
                            }
                        } else {
                            break;
                        }
                    } while (true);
                    Menu.Notification.HeadTable();
                    System.out.println("1:"+data.searchByCode(code));
                    Menu.Notification.Success("Search");
                    return;
                case 2:
                    String name;
                    do {
                        name = InputValid.inputString("\t\tEnter NAME: ");
                        if (!data.isExistCarName(name)) {

                            Menu.Notification.showNotContain("Name: " + name);
                            if (!InputValid.Confirm()) {
                                return;
                            }
                        } else {
                            break;
                        }
                    } while (true);
                    data.searchByName(name).display();
                    Menu.Notification.Success("Search");
                    return;
                case 3:
                    String Owner;
                    do {
                        Owner = InputValid.inputString("\t\tEnter Owner: ");
                        if (!data.isExistCarOwner(Owner)) {
                            Menu.Notification.showNotContain("Owner: " + Owner);
                            if (!InputValid.Confirm()) {
                                return;
                            }
                        } else {
                            break;
                        }
                    } while (true);
                    data.searchByOwner(Owner).display();
                    Menu.Notification.Success("Search");
                    return;
                case 4:
                    String MANUFACTURE;
                    do {
                        MANUFACTURE = InputValid.inputString("\t\tEnter MANUFACTURE: ");
                        if (!data.isExistCarManufacture(MANUFACTURE)) {
                            Menu.Notification.showNotContain("MANUFACTURE: " + MANUFACTURE);
                            if (!InputValid.Confirm()) {
                                return;
                            }
                        } else {
                            break;
                        }
                    } while (true);
                    data.searchByManufacture(MANUFACTURE).display();
                    return;
                case 5:
                    Date dateIn;
                    do {
                        dateIn = InputValid.inputDate("\t\tEnter dateIn: ");
                        if (!data.isExistCarDateIn(dateIn)) {
                            Menu.Notification.showNotContain("dateIn: " + dateIn);
                            if (!InputValid.Confirm()) {
                                return;
                            }
                        } else {
                            break;
                        }
                    } while (true);
                    data.searchByDateIn(dateIn).display();
                    Menu.Notification.Success("Search");
                    return;
                case 6:
                    Date dateOut;
                    do {
                        dateOut = InputValid.inputDate("\t\tEnter dateOut: ");
                        if (!data.isExistCarDateOut(dateOut)) {
                            Menu.Notification.showNotContain("dateOut: " + dateOut);
                            if (!InputValid.Confirm()) {
                                return;
                            }
                        } else {
                            break;
                        }
                    } while (true);
                    data.searchByDateOut(dateOut).display();
                    Menu.Notification.Success("Search");
                    return;
                case 7:
                    return;
            }
        } while (true);
    }

    public void ChoiceSort() {
        Menu.Notification.Header("Sort CAR");
        int choice;
        if (data.getSize() == 0) {
            System.out.println("This list is EMPTY can't sort");
            return;
        }
        do {
            Menu.Notification.SortListFirstTable();
            do {
                choice = InputValid.inputInteger("Which choice you want:");
            } while (choice < 1 || choice > 6);
            switch (choice) {
                case 1:
                    data.sortByCode();
                    Menu.Notification.SortListSecondTable();
                    do {
                        choice = InputValid.inputInteger("Which choice you want:");
                    } while (choice < 1 || choice > 2);
                    switch (choice) {
                        case 1:
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                        case 2:
                            data.reverse();
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                    }
                    return;
                case 2:
                    Menu.Notification.SortListSecondTable();
                    data.sortByName();
                    do {
                        choice = InputValid.inputInteger("Which choice you want:");
                    } while (choice < 1 || choice > 2);
                    switch (choice) {
                        case 1:
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                        case 2:
                            data.reverse();
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                    }
                    return;
                case 3:
                    Menu.Notification.SortListSecondTable();
                    data.sortByOwner();
                    do {
                        choice = InputValid.inputInteger("Which choice you want:");
                    } while (choice < 1 || choice > 2);
                    switch (choice) {
                        case 1:
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                        case 2:
                            data.reverse();
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                    }
                    return;
                case 4:
                    Menu.Notification.SortListSecondTable();
                    data.sortByManufacture();
                    do {
                        choice = InputValid.inputInteger("Which choice you want:");
                    } while (choice < 1 || choice > 2);
                    switch (choice) {
                        case 1:
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                        case 2:
                            data.reverse();
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                    }
                    return;
                case 5:
                    Menu.Notification.SortListSecondTable();
                    data.sortByDateIn();
                    do {
                        choice = InputValid.inputInteger("Which choice you want:");
                    } while (choice < 1 || choice > 2);
                    switch (choice) {
                        case 1:
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                        case 2:
                            data.reverse();
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                    }
                    return;
                case 6:
                    Menu.Notification.SortListSecondTable();
                    data.sortByDateOut();
                    do {
                        choice = InputValid.inputInteger("Which choice you want:");
                    } while (choice < 1 || choice > 2);
                    switch (choice) {
                        case 1:
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                        case 2:
                            data.reverse();
                            data.display();
                            Menu.Notification.Success("Sort");
                            return;
                    }
                    return;
            }
        } while (true);
    }

}