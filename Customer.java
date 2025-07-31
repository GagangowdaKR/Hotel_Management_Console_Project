
public class Customer {
    private String custName;
    private long aadharNo;
    private int roomNo;
    Customer(String custName, long aadharNo, int roomNo){
        this.custName = custName;
        this.aadharNo = aadharNo;
        this.roomNo = roomNo;
    }

    public int getRoomNo(){
        return roomNo;
    }

    public String getName() {
        return custName;
    }
    public long getAadhar(){
        return aadharNo;
    }
}
