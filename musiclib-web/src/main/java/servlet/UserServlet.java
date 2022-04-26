package servlet;



import company.storage.dao.database.databasestorage.DBStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    private DBStorage db;

    @Override
    public void init() throws ServletException {
        try {
            System.out.println("Сервлет создан");
            DriverManager.registerDriver((Driver)Class.forName("org.postgresql.Driver").newInstance());
            db = DBStorage.getInstance();
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        InputStream stream = req.getInputStream();
        InputStreamReader reader = new InputStreamReader(stream);
        StringBuffer res = new StringBuffer();
        for (int data = reader.read(); data != -1; data = reader.read()) {
            res.append((char)data);
        }
        String[] logAndPas = res.toString().split(",");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        if(logAndPas.length == 2 && db.login(logAndPas[0],logAndPas[1]) != 0) {
            pw.println("1");
        }
        else {
            pw.println("0");
        }
        reader.close();
        pw.close();
    }

    @Override
    public void destroy() {
        System.out.println("Сервлет закрыт");
        db.close();
        super.destroy();
    }
}
