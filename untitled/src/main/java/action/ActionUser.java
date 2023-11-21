package action;


import beans.User;
import com.google.gson.Gson;
import dao.DAOUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ActionUser {
    DAOUser daoUser;
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String answer = "";

        String action = request.getParameter("ACTION");
        String[] method = action.split("\\.");

        switch (method[1]){
            case "LOGIN":
                answer = login(request, response);
                System.out.println("Answer login= " + answer);
                break;
            default:
                System.out.println("DEFAULT ACTION");
                break;
        }



        return answer;
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        ArrayList<User> listUser = new DAOUser().findAll(user);
        String jsonUsuario = "";
        Gson gson = new Gson();
        jsonUsuario += "{\"message\": \"Esto es un mensaje de ejemplo\"," +
                "\"usersList\": [";
        if (!listUser.isEmpty()){
            jsonUsuario += gson.toJson(listUser.get(0));
        }
        jsonUsuario += "]}";

        return jsonUsuario;
    }
}
