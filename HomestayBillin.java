// Interface for Room Bill Components
interface RoomBillComponent {
    double FOOD_CHARGE = 500.0;
    double EXTRA_PERSON_CHARGE = 1000.0;
    double TAX = 0.18;
}

// RoomDetails Class
class RoomDetails implements RoomBillComponent {
    private static int counter = 101;
    private int billId;
    private String customerName;
    private String typeOfRoom;
    private int noOfExtraPerson;
    private int noOfDaysOfStay;

    public RoomDetails(String customerName, String typeOfRoom, int noOfExtraPerson, int noOfDaysOfStay) {
        this.billId = counter++;
        this.customerName = customerName;
        this.typeOfRoom = typeOfRoom;
        this.noOfExtraPerson = noOfExtraPerson;
        this.noOfDaysOfStay = noOfDaysOfStay;
    }

    public int getBillId() {
        return billId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getNoOfDaysOfStay() {
        return noOfDaysOfStay;
    }

    public boolean validateNoOfExtraPerson() {
        if (noOfExtraPerson >= 0 && noOfExtraPerson <= 2) {
            return true;
        }
        System.out.println("Error: Extra persons allowed per room are between 0 and 2.");
        return false;
    }

    public boolean validateTypeOfRoom() {
        if (typeOfRoom.equalsIgnoreCase("Standard") ||
            typeOfRoom.equalsIgnoreCase("Deluxe") ||
            typeOfRoom.equalsIgnoreCase("Cottage")) {
            return true;
        }
        System.out.println("Error: Invalid room type. Valid types are Standard, Deluxe, and Cottage.");
        return false;
    }

    public boolean validateNoOfDaysOfStay() {
        if (noOfDaysOfStay >= 1 && noOfDaysOfStay <= 15) {
            return true;
        }
        System.out.println("Error: Number of days of stay must be between 1 and 15.");
        return false;
    }

    public double calculateBill() {
        if (!validateTypeOfRoom() || !validateNoOfDaysOfStay() || !validateNoOfExtraPerson()) {
            return 0.0;
        }

        double baseRoomFare;
        switch (typeOfRoom.toLowerCase()) {
            case "standard":
                baseRoomFare = 2500.0;
                break;
            case "deluxe":
                baseRoomFare = 3500.0;
                break;
            case "cottage":
                baseRoomFare = 5500.0;
                break;
            default:
                baseRoomFare = 0.0;
        }

        double totalBill = (noOfDaysOfStay * baseRoomFare) + (noOfDaysOfStay * FOOD_CHARGE) + (EXTRA_PERSON_CHARGE * noOfExtraPerson);
        totalBill += TAX * totalBill;
        return totalBill;
    }
}

// Tester Class
public class HomestayBillin {
    public static void main(String[] args) {
        // Example input
        String customerName = "John Doe";
        String typeOfRoom = "Deluxe";
        int noOfExtraPerson = 1;
        int noOfDaysOfStay = 5;

        // Create RoomDetails object
        RoomDetails room = new RoomDetails(customerName, typeOfRoom, noOfExtraPerson, noOfDaysOfStay);

        // Calculate total bill
        double totalBill = room.calculateBill();

        // Display details
        if (totalBill > 0) {
            System.out.println("BillId: " + room.getBillId());
            System.out.println("Customer Name: " + room.getCustomerName());
            System.out.println("No. of days of Stay: " + room.getNoOfDaysOfStay());
            System.out.println("Total Bill: " + totalBill);
        }
    }
}
