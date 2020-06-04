package pl.coderslab.charity.user;

//TODO check if can be delete
public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);


}
