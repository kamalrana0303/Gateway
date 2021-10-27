package enums;

public enum DestinationRoute {
    USERS("/user/all");

    private String path;
    DestinationRoute(String path){
        this.path=path;
    }
    public String getPath() {
        return path;
    }
}
