package is.rufan.tournament.domain;

/**
 * Created by Konni on 27.10.2015.
 */
public enum Status {
    CREATED(0,"Created"),
    ACTIVE(1,"Active"),
    OPEN(2,"Open"),
    CLOSED(3,"Closed");

    private final int value;
    private final String name;

    private Status(int value,String name){
        this.value = value;
        this.name = name;
    }

    public int getValue(){
        return value;
    }


    public String getName(){
        return name;
    }

    public static Status fromInt(int n){
        for(Status type : Status.values()){
            if(type.getValue() == n) {
                return type;
            }
        }
        return null;
    }
}
