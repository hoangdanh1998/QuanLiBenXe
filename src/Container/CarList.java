package Container;

import Model.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class CarList extends Vector<Car> {

    private int limit;

    public CarList(int limit) {
        this.limit = limit;
    }

    public Vector<Vector<String>> getVectorStringData() {
        Vector<Vector<String>> myVector = new Vector();

        for (Car thisCar : this) {
            Vector<String> detail = new Vector();
            detail.add(thisCar.getCode());
            detail.add(thisCar.getOwner());
            detail.add(thisCar.getName());
            detail.add(thisCar.getManufacture());
            detail.add(thisCar.dateToString(thisCar.getDateIn()));
            detail.add(thisCar.dateToString(thisCar.getDateOut()));

            myVector.add(detail);
        }
        return myVector;
    }

    public void ChoiceReadFile() {
        do {
            try {
                String fileName = "DATA.sak";
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String detail;
                while ((detail = br.readLine()) != null) {
                    String arr[] = detail.split("[,]");
                    String code = arr[0].trim();
                    String name = arr[1].trim();
                    String owner = arr[2].trim();
                    String manufacture = arr[3].trim();
                    Date DateIn = InputValid.StringToDate(arr[4].trim());
                    Date DateOut = InputValid.StringToDate(arr[5].trim());
                    if (!this.isExistCarCode(code)) {
                        this.add(new Car(code, name, owner, manufacture, DateIn, DateOut));
                    }
                }
                br.close();
                break;
            } catch (Exception ex) {
                System.out.println("Can't read your file.");
            }
        } while (true);
        Menu.Notification.Success("Read file");
    }

    public void ChoiceWriteFile() throws IOException {

        String fileName = "DATA.sak";
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"))) {
            for (Car thisCar : this) {
                writer.write(thisCar.getCode() + "," + thisCar.getName()
                        + "," + thisCar.getOwner() + "," + thisCar.getManufacture()
                        + "," + thisCar.dateToString(thisCar.getDateIn())
                        + "," + thisCar.dateToString(thisCar.getDateOut())
                        + " ");
                writer.newLine();
            }
            writer.close();
        }

    }

    void addCar(Car newCar) {
        this.add(newCar);
    }

    public Boolean isExistCarCode(String code) {
        for (Car thisCar : this) {
            if (thisCar.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    void display() {
        Menu.Notification.HeadTable();
        int count = 0;
        for (Car thisCar : this) {
            count++;
            System.out.print(count + ":" + thisCar);
        }
    }

    void removeCar(String code) {
        for (Car thisCar : this) {
            if (thisCar.getCode().equals(code)) {
                this.remove(thisCar);
                return;
            }
        }
    }

    int getSize() {
        return this.size();
    }

    void modifyCar(Car theCar) {
        for (Car thisCar : this) {
            if (thisCar.getCode().equals(theCar.getCode())) {
                thisCar.setCar(theCar);
            }
        }
    }

    public CarList searchByCode(String code) {
        CarList myList = new CarList(limit);
        for (Car thisCar : this) {
            if (thisCar.getCode().toUpperCase().contains(code.toUpperCase())) {
                myList.addCar(thisCar);
            }
        }
        return myList;
    }

    public CarList searchByName(String name) {
        CarList myList = new CarList(limit);
        for (Car thisCar : this) {
            if (thisCar.getName().toUpperCase().contains(name.toUpperCase())) {
                myList.addCar(thisCar);
            }
        }
        return myList;
    }

    public CarList searchByOwner(String Owner) {
        CarList myList = new CarList(limit);
        for (Car thisCar : this) {
            if (thisCar.getOwner().toUpperCase().contains(Owner.toUpperCase())) {
                myList.addCar(thisCar);
            }
        }
        return myList;
    }

    public CarList searchByManufacture(String Manufacture) {
        CarList myList = new CarList(limit);
        for (Car thisCar : this) {
            if (thisCar.getManufacture().toUpperCase().contains(Manufacture.toUpperCase())) {
                myList.addCar(thisCar);
            }
        }
        return myList;
    }

    public CarList searchByDateIn(Date DateIn) {
        CarList myList = new CarList(limit);
        for (Car thisCar : this) {
            if (thisCar.getDateIn().compareTo(DateIn) == 0) {
                myList.addCar(thisCar);
            }
        }
        return myList;
    }

    public CarList searchByDateOut(Date DateOut) {
        CarList myList = new CarList(limit);
        for (Car thisCar : this) {
            if (Objects.equals(thisCar.getDateOut(), DateOut)) {
                myList.addCar(thisCar);
            }
        }
        return myList;
    }

    void reverse() {
        Collections.reverse(this);
    }

    void sortByCode() {
        Collections.sort(this);
    }

    void sortByName() {
        Collections.sort(this, Car.comByName);
    }

    void sortByOwner() {
        Collections.sort(this, Car.comByOwner);
    }

    void sortByManufacture() {
        Collections.sort(this, Car.comByManufacture);
    }

    void sortByDateIn() {
        Collections.sort(this, Car.comByDateIn);
    }

    void sortByDateOut() {
        Collections.sort(this, Car.comByDateOut);
    }

}
