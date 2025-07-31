import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Hotel hotel = new Hotel();
        System.out.println("Welcome to * Hotel");
        System.out.println("Select the operation : \n 1. Check Availability \n 2. Book Room \n 3. Check Out \n 5. View Details \n 4. Exit");
        int choice = Integer.parseInt(br.readLine());
        do{
            switch (choice){
                case 1:
                    System.out.println("Available Rooms :");
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.println("Welcome to * room booking:");
                    System.out.println("Available Rooms :");
                    hotel.displayAvailableRooms();
                    System.out.println("To Book the Room Enter Room number : ");
                    int roomId = Integer.parseInt(br.readLine());
                    System.out.println("Select the Room type (AC/NAC) : ");
                    String roomType = br.readLine();
                    System.out.println("Enter Customer name :");
                    String name = br.readLine();
                    System.out.println("Enter Aadhar Number : ");
                    long aadhar = Long.parseLong(br.readLine());
                    if(hotel.confirmRoomBooking(roomId, roomType, name, aadhar)){
                        System.out.println("Room Booked !!!");
                    }else{
                        System.out.println("Room is not available");
                    }
                    break;
                case 3:
                    System.out.println("Enter Room No to check out : ");
                    int roomNo = Integer.parseInt(br.readLine());
                    hotel.roomCheckOut(roomNo);
                    break;
                case 4:
                    System.out.println("Enter Room Number : ");
                    roomNo = Integer.parseInt(br.readLine());
                    hotel.displayRoomDetails(roomNo);
                    break;
                case 5:break;
                default:
                    System.out.println("Invalid Choice ");
            }
            System.out.println("\n***********************");
            System.out.println("Select the operation : \n 1. Check Availability \n 2. Book Room \n 3. Check Out \n 4. View Details \n 5. Exit");
            choice = Integer.parseInt(br.readLine());
        }while(choice != 5);
    }
}
