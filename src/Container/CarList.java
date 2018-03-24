
package Container;

import Model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CarList extends Vector<Car> {

    private int limit;

    public CarList(int limit) {
        this.limit = limit;
    }
    public Vector<Vector<String>> getVectorStringData(){
        Vector<Vector<String>> myVector = new Vector();
            
            for (Car thisCar: this){
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

    public Boolean isExistCarName(String Name) {
        for (Car thisCar : this) {
            if (thisCar.getName().equalsIgnoreCase(Name)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isExistCarOwner(String Owner) {
        for (Car thisCar : this) {
            if (thisCar.getOwner().equalsIgnoreCase(Owner)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isExistCarManufacture(String Manufacture) {
        for (Car thisCar : this) {
            if (thisCar.getManufacture().equalsIgnoreCase(Manufacture)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isExistCarDateIn(Date dateIn) {
        for (Car thisCar : this) {

            if (thisCar.getDateIn().equals(dateIn)) {
                return true;
            }
        }
        return false;
    }

    public Boolean isExistCarDateOut(Date DateOut) {
        for (Car thisCar : this) {
            if (!Objects.equals(thisCar.getDateOut(), null)) {
                if (Objects.equals(thisCar.getDateOut(), DateOut)) {
                    return true;
                }
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

    Car searchByCode(String code) {
        Car myCar = null;
        for (Car thisCar : this) {
            if (thisCar.getCode().equals(code)) {
                myCar = thisCar;
            }
        }
        return myCar;
    }

    CarList searchByName(String name) {
        CarList myList = new CarList(limit);
        for (Car thisCar : this) {
            if (thisCar.getName().equalsIgnoreCase(name)) {
                myList.addCar(thisCar);
            }
        }
        return myList;
    }

    CarList searchByOwner(String Owner) {
        CarList myList = new CarList(limit);
        for (Car thisCar : this) {
            if (thisCar.getOwner().equalsIgnoreCase(Owner)) {
                myList.addCar(thisCar);
            }
        }
        return myList;
    }

    CarList searchByManufacture(String manufacture) {
        CarList myList = new CarList(limit);
        for (Car thisCar : this) {
            if (thisCar.getManufacture().equalsIgnoreCase(manufacture)) {
                myList.addCar(thisCar);
            }
        }
        return myList;
    }

    CarList searchByDateIn(Date DateIn) {
        CarList myList = new CarList(limit);
        for (Car thisCar : this) {
            if (thisCar.getDateIn().compareTo(DateIn) == 0) {
                myList.addCar(thisCar);
            }
        }
        return myList;
    }

    CarList searchByDateOut(Date DateOut) {
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
