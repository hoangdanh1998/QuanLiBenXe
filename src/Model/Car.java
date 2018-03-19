package Model;

import java.util.*;
import java.lang.Comparable;
import java.text.SimpleDateFormat;

public class Car implements Comparable {

    private String owner, code, name, manufacture;
    private Date dateIn, dateOut;

    public Car() {
        owner = code = name = manufacture = null;
        dateIn = dateOut = null;
    }

    public Car(String code, String name, String owner, String manufacture, Date dateIn, Date dateOut) {
        this.code = code;
        this.owner = owner;
        this.name = name;
        this.manufacture = manufacture;
        this.dateIn = dateIn;
        this.dateOut = dateOut;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public void setCar(Car theCar) {
        code = theCar.getCode();
        name = theCar.getName();
        owner = theCar.getOwner();
        manufacture = theCar.getManufacture();
        dateIn = theCar.getDateIn();
        dateOut = theCar.getDateOut();
        
    }

    @Override
    public int compareTo(Object SecondCar) {
        return ((Car) this).code.compareTo(((Car) SecondCar).code);
    }
    public static Comparator comByName = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return ((Car) o1).name.compareTo(((Car) o2).name);
        }
    };
    public static Comparator comByDateIn = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return ((Car) o1).dateIn.compareTo(((Car) o2).dateIn);
        }
    };
    //DATE out we must check NULL   
    public static Comparator comByDateOut = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            if (((Car) o1).dateOut == null) {
                return -1;
            }
            if (((Car) o2).dateOut == null) {
                return 1;
            }
            return ((Car) o1).dateOut.compareTo(((Car) o2).dateOut);
        }
    };

    public static Comparator comByManufacture = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return ((Car) o1).manufacture.compareTo(((Car) o2).manufacture);
        }
    };
    public static Comparator comByOwner = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return ((Car) o1).owner.compareTo(((Car) o2).owner);
        }
    };

    public String dateToString(Date myDate) {
        if (myDate == null) {
            return "";
        } else {
            return myDate.getDate() + "/" + (myDate.getMonth() + 1) + "/" + myDate.getYear();
        }
    }

    @Override
    public String toString() {
        return String.format("   < %10s | %20s | %15s | %15s | %10s |%10s > \n", this.code, this.name, this.owner, this.manufacture, dateToString(this.dateIn), dateToString(this.dateOut));
    }

}
