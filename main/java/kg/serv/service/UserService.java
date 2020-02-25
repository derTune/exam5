package kg.serv.service;

import kg.serv.dao.UserDao;
import kg.serv.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/service")
public class UserService {
    private UserDao userDao = new UserDao();
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return userDao.getUserList();
    }

    @POST
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String showUser(@PathParam("userId") int userName) {
        return "";
    }

    @POST
    @Path("/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String showUser(@PathParam("userName") String userName) {
        return "";
    }

}
