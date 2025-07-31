
public class Rooms{
    private int roomNo;
    private String roomType;
    private boolean availability;
    private int price;

    Rooms(int roomNo, String roomType, boolean availability, int price){
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.availability = availability;
        this.price = price;
    }

    public boolean getAvailablity(){
        return availability;
    }
    public void setAvailability(boolean status){
        this.availability = status;
    }

    public int getRoomNo(){
        return roomNo;
    }
    public String getRoomType(){
        return roomType;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }

    public void setRoomType(String type) {
        this.roomType = type;
    }
}
