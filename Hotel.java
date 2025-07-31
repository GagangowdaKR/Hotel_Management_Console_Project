import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hotel {
    private String hotelName;
    private String hotelAddress;
    private  ArrayList<Rooms> allRooms = new ArrayList<>();
    private  ArrayList<Customer> customers = new ArrayList<>();

    {
        allRooms.add(new Rooms(100, "", true, 0));
        allRooms.add(new Rooms(101, "", true, 0));
        allRooms.add(new Rooms(102, "", true, 0));
        allRooms.add(new Rooms(103, "", true, 0));
        allRooms.add(new Rooms(104, "", true, 0));

        allRooms.add(new Rooms(105, "", true, 0));
        allRooms.add(new Rooms(106, "", true, 0));
        allRooms.add(new Rooms(107, "", true, 0));
        allRooms.add(new Rooms(108, "", true, 0));
        allRooms.add(new Rooms(109, "", true, 0));
    }

    public void displayAvailableRooms(){
        allRooms.stream().filter(rooms -> (rooms.getAvailablity() == true) )
                .forEach(room -> System.out.println("Room No : "+room.getRoomNo()));
    }

//    public boolean isAvailable(int roomNo){
//        for(Rooms room : allRooms){
//            if(room.getRoomNo() == roomNo){
//                return room.getAvailablity();
//            }
//        }
//        return false;
//    }
    public boolean isAvailable(int roomNo){
        return allRooms.stream()
                .anyMatch(room -> room.getRoomNo()==roomNo && room.getAvailablity());
    }


//    public boolean confirmRoomBooking(int roomNo, String roomType, String name, long aadhar){
//        if(isAvailable(roomNo)){
//            int price = (roomType.equalsIgnoreCase("AC")) ? 2500 : 2000;
//            for(int i=0; i<allRooms.size(); i++) {
//                if (allRooms.get(i).getRoomNo() == roomNo){
//                    allRooms.set(i, new Rooms(roomNo, roomType, false, price));
//                    break;
//                }
//            }
//            customers.add(new Customer(name, aadhar, roomNo));
//            return true;
//        }
//        return false;
//    }
    public boolean confirmRoomBooking(int roomNo, String roomType, String name, long aadhar){
        if(isAvailable(roomNo)){
            int price = roomType.equalsIgnoreCase("AC") ? 2500 : 2000;
            allRooms.stream()
                    .filter(room -> room.getRoomNo() == roomNo)
                    .forEach(room -> {
                        room.setAvailability(false);
                        room.setPrice(price);
                        room.setRoomType(roomType);
                    });
            customers.add(new Customer(name, aadhar, roomNo));
            return true;
        }
        return false;
    }

//    public void roomCheckOut(int roomNo) {
//        if(! isAvailable(roomNo)){
//            int index = 0;
//            for(Customer customer : customers){
//                if(customer.getRoomNo() == roomNo){
//                    customers.remove(index);
//                    break;
//                }
//                index++;
//            }
//            for(Rooms room : allRooms){
//                if(room.getRoomNo() == roomNo){
//                    room.setRoomType();
//                    room.setAvailability();
//                    room.setPrice();
//                    break;
//                }
//            }
//            System.out.println("Successfully checked out from the Hotel..");
//        }else{
//            System.out.println("Currently Room is not Booked !!");
//        }
//    }
    public void roomCheckOut(int roomNo){
        if(roomNo > 109 || roomNo<100){
            System.out.println("Room is not valid!! Check the room number....");
            return;
        }
        if(!isAvailable(roomNo)){
            customers.removeIf(customer -> customer.getRoomNo() == roomNo);
            allRooms.stream()
                    .filter(room -> room.getRoomNo()==roomNo).forEach(room ->{
                        room.setAvailability(true);
                        room.setPrice(0);
                        room.setRoomType("");
                    });
            System.out.println("Room checkout completed !!");
        }else{
            System.out.println("Currently Room is not booked !!");
        }
    }


//    public Rooms getRoom(int roomNo){
//        for(Rooms room : allRooms){
//            if(room.getRoomNo() == roomNo){
//                return room;
//            }
//        }
//        return null;
//    }
    public Optional<Rooms> getRoom(int roomNo){
        return allRooms.stream()
                .filter(room -> room.getRoomNo()==roomNo)
                .findFirst();
    }


//    public Customer getCustomer(int roomNo){
//        for(Customer customer : customers){
//            if(customer.getRoomNo() == roomNo){
//                return customer;
//            }
//        }
//        return null;
//    }
    public Optional<Customer> getCustomer(int roomNo){
       return customers.stream()
               .filter(customer -> customer.getRoomNo()==roomNo)
               .findFirst();
    }


//    public void displayRoomDetails(int roomNo) {
//        Rooms room = getRoom(roomNo);
//        Customer customer = getCustomer(roomNo);
//        if(isAvailable(roomNo)){
//            System.out.println("This Room is not Booked!!");
//            return;
//        }
//        if(roomNo < 100 || roomNo > 109) {
//            System.out.println("Room Number is not valid..");
//            return;
//        }
//        System.out.println("\nRoom Booked Details : ");
//        System.out.println("Room Number : "+room.getRoomNo());
//        System.out.println("Room Type   : "+room.getRoomType());
//        System.out.println("Room price  : "+room.getPrice());
//        System.out.println("CustomerName: "+customer.getName());
//        System.out.println("aadhar No   : "+customer.getAadhar());
//    }
    public void displayRoomDetails(int roomNo){
        if(isAvailable(roomNo)){
            System.out.println("This Room is not Booked!!");
        }else if(roomNo > 109 || roomNo<100){
            System.out.println("Room Number is not valid..");
        }else{
            Rooms room = getRoom(roomNo).orElseThrow(() -> new RuntimeException("Room not found!"));
            getCustomer(roomNo).ifPresent(customer -> {
                System.out.println("\nRoom Booked Details : ");
                System.out.println("Room Number : " + room.getRoomNo());
                System.out.println("Room Type   : " + room.getRoomType());
                System.out.println("Room price  : " + room.getPrice());
                System.out.println("CustomerName: " + customer.getName());
                System.out.println("Aadhar No   : " + customer.getAadhar());
            });
        }
    }
}
