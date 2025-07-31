import java.util.ArrayList;

public class Hotel {
    private String hotelName;
    private String hotelAddress;
    private  ArrayList<Rooms> allRooms = new ArrayList<>();
    private  ArrayList<Customer> customers = new ArrayList<>();

    {
        allRooms.add(new Rooms(100, "", true, 2500));
        allRooms.add(new Rooms(101, "", true, 2500));
        allRooms.add(new Rooms(102, "", true, 2500));
        allRooms.add(new Rooms(103, "", true, 2500));
        allRooms.add(new Rooms(104, "", true, 2500));

        allRooms.add(new Rooms(105, "", true, 2000));
        allRooms.add(new Rooms(106, "", true, 2000));
        allRooms.add(new Rooms(107, "", true, 2000));
        allRooms.add(new Rooms(108, "", true, 2000));
        allRooms.add(new Rooms(109, "", true, 2000));
    }

    public void displayAvailableRooms(){
        allRooms.stream().filter(rooms -> (rooms.getAvailablity() == true) ).forEach(room -> System.out.println("Room No : "+room.getRoomNo()));
    }

    public boolean isAvailable(int roomNo){
        for(Rooms room : allRooms){
            if(room.getRoomNo() == roomNo){
                return room.getAvailablity();
            }
        }
        return false;
    }

    public boolean confirmRoomBooking(int roomNo, String roomType, String name, long aadhar){
        if(isAvailable(roomNo)){
            int price = (roomType.equalsIgnoreCase("AC")) ? 2500 : 2000;
            for(int i=0; i<allRooms.size(); i++) {
                if (allRooms.get(i).getRoomNo() == roomNo){
                    allRooms.set(i, new Rooms(roomNo, roomType, false, price));
                    break;
                }
            }
            customers.add(new Customer(name, aadhar, roomNo));
            return true;
        }
        return false;
    }


    public void roomCheckOut(int roomNo) {
        if(! isAvailable(roomNo)){
            int index = 0;
            for(Customer customer : customers){
                if(customer.getRoomNo() == roomNo){
                    customers.remove(index);
                    break;
                }
                index++;
            }
            for(Rooms room : allRooms){
                if(room.getRoomNo() == roomNo){
                    room.setRoomType();
                    room.setAvailability();
                    room.setPrice();
                    break;
                }
            }
            System.out.println("Successfully checked out from the Hotel..");
        }else{
            System.out.println("Currently Room is not Booked !!");
        }
    }

    public Rooms getRoom(int roomNo){
        for(Rooms room : allRooms){
            if(room.getRoomNo() == roomNo){
                return room;
            }
        }
        return null;
    }
    public Customer getCustomer(int roomNo){
        for(Customer customer : customers){
            if(customer.getRoomNo() == roomNo){
                return customer;
            }
        }
        return null;
    }
    public void displayRoomDetails(int roomNo) {
        Rooms room = getRoom(roomNo);
        Customer customer = getCustomer(roomNo);
        if(isAvailable(roomNo)){
            System.out.println("This Room is not Booked!!");
            return;
        }
        if(roomNo < 100 || roomNo > 109) {
            System.out.println("Room Number is not valid..");
            return;
        }

        System.out.println("\nRoom Booked Details : ");
        System.out.println("Room Number : "+room.getRoomNo());
        System.out.println("Room Type   : "+room.getRoomType());
        System.out.println("Room price  : "+room.getPrice());
        System.out.println("CustomerName: "+customer.getName());
        System.out.println("aadhar No   : "+customer.getAadhar());
    }
}
