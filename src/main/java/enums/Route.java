package enums;

public enum Route {

    USERS("/users");


    private  String path;
    Route(String name){
        this.path=name;
    }

    public String getPath() {
        return path;
    }
}
