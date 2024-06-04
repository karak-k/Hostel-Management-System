import java.util.*;
import java.io.*;
class Customer{
    private String First_name;
    private String Last_name;
    private long  Id_num;

    public Customer(String First_name,String Last_name,long Id_num)
    {
        this.First_name=First_name;
        this.Last_name=Last_name;
        this.Id_num=Id_num;
    }

      // Getters
    public String getFirstName() {
        return First_name;
    }

    public String getLastName() {
        return Last_name;
    }

    public long getAadharNumber() {
        return Id_num;
    }

    public static Customer inputCustomerDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        long aadharNumber;
        do {
            System.out.print("Enter 12-digit Aadhar number: ");
            aadharNumber = scanner.nextLong();
            if (String.valueOf(aadharNumber).length() != 12) {
                System.out.println("Aadhar number should be 12 digits long. Please try again.");
            }
        } while (String.valueOf(aadharNumber).length() != 12);

        // scanner.close();
        
        return new Customer(firstName, lastName, aadharNumber);
    }
}

class Room_Info{

 public void display(){
    HashMap<String, Vector<String>> mp = new HashMap<>();
    Vector<String> vector1 = new Vector<>();
    Vector<String> vector2 = new Vector<>();
    Vector<String> vector3 = new Vector<>();

    vector1.add("1.a");
    vector1.add("2.b");
    vector1.add("3.c");
    vector1.add("4.d");
     mp.put("Gold",vector1);
    vector2.add("1.w");
    vector2.add("2.x");
    vector2.add("3.y");
    vector2.add("4.z");
    mp.put("Silver",vector2);
    vector3.add("1.e");
    vector3.add("2.f");
    vector3.add("3.g");
    vector3.add("4.h");
    mp.put("Bronze",vector3);

    System.out.println("Type of rooms:");
    for (Map.Entry<String, Vector<String>> entry : mp.entrySet()) {
        String key = entry.getKey();
        Vector<String> values = entry.getValue();
        
        System.out.println(key);
        System.out.println("Services:");
        for (String value : values) {
            System.out.println(value);
        }
    }
    
 }
}

class CustomerAndPriceDetails {
    private HashMap<Long, HashMap<String, ArrayList<Object>>> customerData;
    private ArrayList<Object> priceDetails;

    public CustomerAndPriceDetails(HashMap<Long, HashMap<String, ArrayList<Object>>> customerData, ArrayList<Object> priceDetails) {
        this.customerData = customerData;
        this.priceDetails = priceDetails;
    }

    public HashMap<Long, HashMap<String, ArrayList<Object>>> getCustomerData() {
        return customerData;
    }

    public ArrayList<Object> getPriceDetails() {
        return priceDetails;
    }
}



class hotel_booking{

     ArrayList<Customer> myObjects = new ArrayList<>();
    
    
        public CustomerAndPriceDetails customerDetails() {
            boolean checkOut = true;
            Scanner sc = new Scanner(System.in);
    
            HashMap<Long, HashMap<String, ArrayList<Object>>> map = new HashMap<>();
            ArrayList<Object> priceDetails = new ArrayList<>();
    
            int totalBill = 0;
            while (checkOut) {
                Room_Info obj = new Room_Info();
                obj.display();
    
                Customer customer = Customer.inputCustomerDetails();
                myObjects.add(customer);
                HashMap<String, ArrayList<Object>> map2 = new HashMap<>();
                boolean continueLoop = true;
                int Total_days;
                int Total_Rooms;
    
                while (continueLoop) {
                    System.out.println(" ");
                    System.out.println("Please Select the Room :");
                    System.out.println("1. Gold");
                    System.out.println("2. Silver");
                    System.out.println("3. Bronze");
                    System.out.println("4. Exit");
    
                    ArrayList<String> services = new ArrayList<>();
                    int select = sc.nextInt();
    
                    switch (select) {
                        case 1:
                            ArrayList<Object> list1 = new ArrayList<>();
                            System.out.println("Enter the number of days:");
                            Total_days = sc.nextInt();
                            list1.add(Total_days);
                            System.out.println("Enter the number of rooms:");
                            Total_Rooms = sc.nextInt();
                            list1.add(Total_Rooms);
                            Room goldRoom = new Room(Total_days, Total_Rooms, "Gold");
                            services = goldRoom.displayServices();
                            list1.add(services);
                            totalBill = goldRoom.totalBillCalculator();
                            map2.put("Gold", list1);
                            break;
                        case 2:
                            ArrayList<Object> list2 = new ArrayList<>();
                            System.out.println("Enter the number of days:");
                            Total_days = sc.nextInt();
                            list2.add(Total_days);
                            System.out.println("Enter the number of rooms:");
                            Total_Rooms = sc.nextInt();
                            list2.add(Total_Rooms);
                            Room silverRoom = new Room(Total_days, Total_Rooms, "Silver");
                            services = silverRoom.displayServices();
                            list2.add(services);
                            totalBill = silverRoom.totalBillCalculator();
                            map2.put("Silver", list2);
                            break;
                        case 3:
                            ArrayList<Object> list3 = new ArrayList<>();
                            System.out.println("Enter the number of days:");
                            Total_days = sc.nextInt();
                            list3.add(Total_days);
                            System.out.println("Enter the number of rooms:");
                            Total_Rooms = sc.nextInt();
                            list3.add(Total_Rooms);
                            Room bronzeRoom = new Room(Total_days, Total_Rooms, "Bronze");
                            services = bronzeRoom.displayServices();
                            list3.add(services);
                            totalBill = bronzeRoom.totalBillCalculator();
                            map2.put("Bronze", list3);
                            break;
                        case 4:
                            continueLoop = false;
                            break;
                        default:
                            System.out.println("Please choose a valid room");
                            break;
                    }
                }
    
                System.out.println("Your Total Bill Is " + totalBill + " Rs.");
                map.put(customer.getAadharNumber(), map2);
    
                ArrayList<Object> priceDetail = new ArrayList<>();
                priceDetail.add(customer.getAadharNumber());
                priceDetail.add(totalBill);
                priceDetails.add(priceDetail);
    
                System.out.println("Do you want to exit:");
                String res = sc.next();
                if (res.equals("yes"))
                    checkOut = false;
            }
    
            sc.close();
            return new CustomerAndPriceDetails(map, priceDetails);
        }
    }
    
    


 class CSVWriter {
    public static void writePriceDetail(ArrayList<Object>priceDetail,String pathFile)
   {
     
    try (FileWriter writer = new FileWriter(pathFile)){
  
        writer.append("ID_NUM,TotalPrice\n");

      

            writer.append(priceDetail.get(0) + "," + priceDetail.get(1) + "\n");
        
        System.out.println("CustomerData has been written to " + pathFile);
    }
    catch (IOException e) {
        e.printStackTrace();
    }
   }
   public static void writeCustomerData(ArrayList<Customer>CustomerData,String filePath)
   {
     
    try (FileWriter writer = new FileWriter(filePath)){
  
        writer.append("First_Name,Last_Name,AadharNumber\n");

        for (Customer customer : CustomerData) {

            writer.append(customer.getFirstName() + "," + customer.getLastName() + "," + customer.getAadharNumber()  + "\n");
        }
        System.out.println("CustomerData has been written to " + filePath);
    }
    catch (IOException e) {
        e.printStackTrace();
    }
   }
    public static void writeDataToCSV(HashMap<Long, HashMap<String, ArrayList<Object>>> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write CSV header
            writer.append("Customer ID,Room Type,Total Days,Total Rooms,Services\n");

            // Write data rows
            for (long customerId : data.keySet()) {
                HashMap<String, ArrayList<Object>> customerData = data.get(customerId);
                for (String roomType : customerData.keySet()) {
                    ArrayList<Object> roomDetails = customerData.get(roomType);
                    int totalDays = (int) roomDetails.get(0);
                    int totalRooms = (int) roomDetails.get(1);
                    ArrayList<String> services = (ArrayList<String>) roomDetails.get(2);
                    String servicesString = String.join(", ", services);
                    writer.append(customerId + "," + roomType + "," + totalDays + "," + totalRooms + "," + servicesString + "\n");
                }
            }

            System.out.println("Data has been written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Hotel_Management {
    public static void main(String[] args) {
      
         hotel_booking objBooking=new hotel_booking();

         CustomerAndPriceDetails data = objBooking.customerDetails();
         
         HashMap<Long, HashMap<String, ArrayList<Object>>> customerData = data.getCustomerData();
         ArrayList<Customer> customerList = objBooking.myObjects;
         ArrayList<Object> priceDetails = data.getPriceDetails();

         for (Customer customer : customerList) {
            System.out.println("Customer details:");
            System.out.println("First Name: " + customer.getFirstName());
            System.out.println("Last Name: " + customer.getLastName());
            System.out.println("Aadhar Number: " + customer.getAadharNumber());
        }

        System.out.println();
    
         for (Map.Entry<Long, HashMap<String, ArrayList<Object>>> entry : customerData.entrySet()) {
            Long customerId = entry.getKey();
            HashMap<String, ArrayList<Object>> customerRoomData = entry.getValue();

            System.out.println("Customer ID: " + customerId);

            // Iterate over the inner map
            for (Map.Entry<String, ArrayList<Object>> roomEntry : customerRoomData.entrySet()) {
                String roomType = roomEntry.getKey();
                ArrayList<Object> roomDetails = roomEntry.getValue();

                System.out.println("Room Type: " + roomType);
                System.out.println("Room Details:");

                // Extract and print room details
                for (Object detail : roomDetails) {
                    System.out.println(detail);
                }
            }
            System.out.println();
        }

        String filePath = "./data.csv";
        String filepath = "./cutomerData.csv";
        String pathFile = "./priceDetail.csv";
        
        CSVWriter.writeCustomerData(customerList, filepath);
        CSVWriter.writeDataToCSV(customerData, filePath);
        CSVWriter.writePriceDetail(priceDetails, pathFile);
    }
}
