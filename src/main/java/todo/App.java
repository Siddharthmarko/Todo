package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TodoServlet")
public class App extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection conn;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void init() {
        String url = "jdbc:mysql://localhost:3306/toddo";
        String user = "root";
        String password = "12346";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/insert":
                    insertTodo(request, response);
                    break;
                case "/update":
                    updateTodo(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void insertTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        String action = request.getServletPath();
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
        Date targetDate = sdf.parse(request.getParameter("targetDate"));

        PreparedStatement stmt = conn
                .prepareStatement("INSERT INTO todos (title, description, is_done, target_date) VALUES (?, ?, ?, ?)");
        stmt.setString(1, title);
        stmt.setString(2, description);
        stmt.setBoolean(3, isDone);
        stmt.setDate(4, new java.sql.Date(targetDate.getTime()));
        stmt.executeUpdate();
        response.sendRedirect(action + "/list");
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        String action = request.getServletPath();

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean isDone = Boolean.parseBoolean(request.getParameter("isDone"));
        Date targetDate = sdf.parse(request.getParameter("targetDate"));

        PreparedStatement stmt = conn.prepareStatement(
                "UPDATE todos SET title = ?, description = ?, is_done = ?, target_date = ? WHERE id = ?");
        stmt.setString(1, title);
        stmt.setString(2, description);
        stmt.setBoolean(3, isDone);
        stmt.setDate(4, new java.sql.Date(targetDate.getTime()));
        stmt.setInt(5, id);
        stmt.executeUpdate();
        response.sendRedirect(action + "/list");

    }
}
