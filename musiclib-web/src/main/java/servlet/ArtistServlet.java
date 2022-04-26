package servlet;

import company.storage.dao.database.databasestorage.DBStorage;
import company.storage.dao.DaoType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


@WebServlet("/ArtistServlet")
public class ArtistServlet extends HttpServlet {

    private DBStorage db;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void init() {
        try {
            DriverManager.registerDriver((Driver)Class.forName("org.postgresql.Driver").newInstance());
            db = DBStorage.getInstance();
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        String json = gson.toJson(db.get(DaoType.ARTIST).values());
        pw.println(json);
        pw.close();
    }

    public void destroy() {
        System.out.println("Сервлет закрыт");
        db.close();
        super.destroy();
    }
}
