package api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.CustomerDTO;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerAPI extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        String test = req.getHeader("User-Agent");
        BufferedReader reader = req.getReader();

        Gson gson = new Gson();

        ArrayList<CustomerDTO> customerDTOS = gson.fromJson(reader, new TypeToken<ArrayList<CustomerDTO>>(){});
        System.out.println(customerDTOS);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
