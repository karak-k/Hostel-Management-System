import java.util.*;

interface Gold {
    int RoomPrice = 5000;
    static final Vector<String> services = new Vector<>(Arrays.asList(
            "Premium_SPA", "Pool", "Bar", "premiumLaundary", "Food"));

    default HashMap<String, Integer> getServicesMap() {
        HashMap<String, Integer> mp = new HashMap<>();
        mp.put("Premium_SPA", 1500);
        mp.put("Pool", 1000);
        mp.put("Bar", 3000);
        mp.put("premiumLaundary", 700);
        mp.put("Food", 1000);
        return mp;
    }

}

interface Silver {
    int RoomPrice = 4000;
    static final Vector<String> services = new Vector<>(Arrays.asList(
            "Super_SPA", "miniBar", "smokingArea", "starLaundary", "foodService"));

    default HashMap<String, Integer> getServicesMap() {
        HashMap<String, Integer> mp = new HashMap<>();
        mp.put("Super_SPA", 1000);
        mp.put("miniBar", 1500);
        mp.put("smokingArea", 500);
        mp.put("starLaundary", 300);
        mp.put("foodService", 1000);
        return mp;
    }
}

interface Bronze {
    int RoomPrice = 1500;
    static final Vector<String> services = new Vector<>(Arrays.asList(
            "wifi", "smartTV", "bathTub", "laundary", "Food"));

    default HashMap<String, Integer> getServicesMap() {
        HashMap<String, Integer> mp = new HashMap<>();
        mp.put("wifi", 200);
        mp.put("smartTV", 400);
        mp.put("bathTub", 500);
        mp.put("laundary", 200);
        mp.put("Food", 500);
        return mp;
    }
}

public class Room implements Gold, Silver, Bronze {

    private int Total_days;
    private int Total_Rooms;
    private String roomType;
    static int costTotal = 0; // Static variable to track the total cost

    public Room(int Total_days, int Total_Rooms, String roomType) {
        this.Total_days = Total_days;
        this.Total_Rooms = Total_Rooms;
        this.roomType = roomType;
    }

    public HashMap<String, Integer> getServicesMap() {
        HashMap<String, Integer> mp = new HashMap<>();
        if (roomType.equals("Gold")) {
            mp.putAll(Gold.super.getServicesMap());
        } else if (roomType.equals("Silver")) {
            mp.putAll(Silver.super.getServicesMap());
        } else {
            mp.putAll(Bronze.super.getServicesMap());
        }
        return mp;
    }

    public ArrayList<String> displayServices() {
        Vector<String> main_Service = new Vector<>();
        HashMap<String, Integer> servicesMap = getServicesMap();
        
        ArrayList<String>storeServices=new ArrayList<>();

        if (roomType.equals("Gold")) {
            main_Service = Gold.services;
        } else if (roomType.equals("Silver")) {
            main_Service = Silver.services;
        } else {
            main_Service = Bronze.services;
        }
        Scanner sc = new Scanner(System.in);
        int room = 1;
        int rooms = Total_Rooms;
        while (rooms > 0) {
            int day = 1;
            int days = Total_days;
            while (days > 0) {
                System.out.println("Choose services you want for Room " + room + " on day " + day);
                System.out.println("Available services:");
                for (int i = 0; i < main_Service.size(); i++) {
                    System.out.println((i + 1) + ". " + main_Service.get(i));
                }

                boolean check = true;
                while (check) {
                    int choose = sc.nextInt();
                    switch (choose) {
                        case 1:
                            System.out.println(main_Service.get(0));
                            storeServices.add(main_Service.get(0));
                            costTotal += servicesMap.get(main_Service.get(0));
                            // System.out.println(costTotal);
                            break;
                        case 2:
                            System.out.println(main_Service.get(1));
                            storeServices.add(main_Service.get(1));
                            costTotal += servicesMap.get(main_Service.get(1));
                            // System.out.println(costTotal);
                            break;
                        case 3:
                            System.out.println(main_Service.get(2));
                            storeServices.add(main_Service.get(2));
                            costTotal += servicesMap.get(main_Service.get(2));
                            // System.out.println(costTotal);
                            break;
                        case 4:
                            System.out.println(main_Service.get(3));
                            storeServices.add(main_Service.get(3));
                            costTotal += servicesMap.get(main_Service.get(3));
                            // System.out.println(costTotal);
                            break;
                        case 5:
                            System.out.println(main_Service.get(4));
                            storeServices.add(main_Service.get(4));
                            costTotal += servicesMap.get(main_Service.get(4));
                            // System.out.println(costTotal);
                            break;
                        case 6:
                            check = false;
                            break;
                        default:
                            break;
                    }
                }
                days--;
                day++;
            }

            rooms--;
            room++;
        }
        return storeServices;
    }

    public Integer totalBillCalculator() {

        if (roomType.equals("Gold")) {
            costTotal += Gold.RoomPrice * Total_days * Total_Rooms;

        } else if (roomType.equals("Silver")) {
            costTotal += Silver.RoomPrice * Total_days * Total_Rooms;
        } else {
            costTotal += Bronze.RoomPrice * Total_days * Total_Rooms;
        }

        // System.out.println(costTotal);

        return costTotal;
    }

}
