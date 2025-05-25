package libraryManagement;

enum UserStatus {
    ACTIVE,
    WARNING,
    SUSPENDED
}

public class User {

    String userName;
    String userId;
    UserStatus userStatus;

    public User(String userID, String userName) {
        this.userId = userID;
        this.userName = userName;
        this.userStatus = UserStatus.ACTIVE;

    }
}
