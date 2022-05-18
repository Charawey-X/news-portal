import Exceptions.ApiExceptions;
import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oDepartmentNewsDao;
import dao.Sql2oNewsDao;
import dao.Sql2oEmployeeDao;
import models.Department;
import models.DepartmentNews;
import models.News;
import models.Employee;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        Sql2oDepartmentNewsDao departmentNewsDao;
        Sql2oNewsDao newsDao;
        Sql2oEmployeeDao employeeDao;
        Sql2oDepartmentDao departmentDao;
        Connection conn;
        Gson gson = new Gson();

        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "x", "230620");


        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        employeeDao = new Sql2oEmployeeDao(sql2o);


        //add a department
        post("/departments/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            departmentDao.add(department);
            res.status(201);
            return gson.toJson(department);
        });

        //view all departments
        get("/departments", "application/json", (req, res) -> {
            res.type("application/json");
            return gson.toJson(departmentDao.getAll());
        });

        //find a department
        get("/departments/:id", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null ){
                throw new ApiExceptions(404, String.format("No department with id: %s exists", req.params("id")));
            }
            return gson.toJson(departmentToFind);
        });

        //update a department
        post("/department/:id/update", "application/json", (request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            department.setId(Integer.parseInt(request.params("id")));
            departmentDao.update(Integer.parseInt(request.params("id")), department);
            response.status(201);
            response.type("application/json");
            return gson.toJson(department);
        });

        //delete a department
        get("/delete/:id/department", "application/json", (request, response) -> {
            departmentDao.deleteById(Integer.parseInt(request.params("id")));
            response.status(201);
            response.type("application/json");
            return gson.toJson(departmentDao.getAll());
        });

        //add an employee
        post("/departments/:id/users/new", "application/json", (req, res) -> {
            Employee employee = gson.fromJson(req.body(), Employee.class);
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null ){
                throw new ApiExceptions(404, String.format("No department with id: %s exists", req.params("id")));
            } else {
                employee.setDepartmentId(departmentId);
                employeeDao.add(employee);
                res.status(201);
                return gson.toJson(employee);
            }
        });

        //view employees
        get("/users", "application/json", (req, res) -> {
            if (employeeDao.getAll().size() == 0) {
                return "{\"message\":\"I'm sorry, but no employees are here yet.\"}";
            } else{
                return gson.toJson(employeeDao.getAll());
            }
        });

        //find an employee
        get("/users/:id", "application/json", (req, res) -> {
            int employeeId = Integer.parseInt(req.params("id"));
            Employee employeeToFind = employeeDao.findById(employeeId);
            Department department = departmentDao.findById(employeeToFind.getDepartmentId());
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("employee", employeeToFind);
            jsonMap.put("department", department);
            return gson.toJson(jsonMap);
        });

        //update an employee
        post("/user/:id/update", "application/json", (request, response) -> {
            Employee employee = gson.fromJson(request.body(), Employee.class);
            employee.setId(Integer.parseInt(request.params("id")));
            employeeDao.update(Integer.parseInt(request.params("id")), employee);
            response.status(201);
            response.type("application/json");
            return gson.toJson(employee);
        });

        //remove an employee
        get("/delete/:id/user", "application/json", (request, response) -> {
            employeeDao.deleteById(Integer.parseInt(request.params("id")));
            response.status(201);
            response.type("application/json");
            return gson.toJson(employeeDao.getAll());
        });

        //add news
        post("/news/new", "application/json", (req, res) -> {
            News news = gson.fromJson(req.body(), News.class);
            newsDao.add(news);
            res.status(201);
            return gson.toJson(news);
        });

        //view all news
        get("/news", "application/json", (req, res) -> {
            if (newsDao.getAll().size() == 0) {
                return "{\"message\":\"I'm sorry, but no news are currently listed.\"}";
            } else{
                return gson.toJson(newsDao.getAll());
            }
        });

        //add news by department
        post("/departments/:id/news/new", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null ){
                throw new ApiExceptions(404, String.format("No department with id: %s exists", req.params("id")));
            } else {
                DepartmentNews news = gson.fromJson(req.body(), DepartmentNews.class);
                news.setDepartmentId(departmentId);
                departmentNewsDao.add(news);
                res.status(201);
                return gson.toJson(news);
            }
        });

        //view news by department
        get("/departments/:id/news", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            List<DepartmentNews> news = departmentDao.getAllNewsByDepartment(departmentToFind.getId());
            if (departmentToFind == null ){
                throw new ApiExceptions(404, String.format("No department with id: %s exists", req.params("id")));
            } else if(news.size() == 0) {
                return "{\"message\":\"I'am sorry there are no news in this department currently.\"}";
            } else {
                return gson.toJson(news);
            }
        });

        //update news item
        post("/news/:id/update", "application/json", (request, response) -> {
            News news = gson.fromJson(request.body(), News.class);
            news.setId(Integer.parseInt(request.params("id")));
            newsDao.update(Integer.parseInt(request.params("id")), news);
            response.status(201);
            response.type("application/json");
            return gson.toJson(news);
        });

        //delete news item
        get("news/:id/delete", "application/json", (request, response) -> {
            newsDao.deleteById(Integer.parseInt(request.params("id")));
            return gson.toJson(newsDao.getAll());
        });

        //to see employees of a given department and link to their news(that department news)

        //add news by employees in a department
        get("/departments/:id/users/news", "application/json", (req, res) -> {
            int departmentId = Integer.parseInt(req.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if (departmentToFind == null ){
                throw new ApiExceptions(404, String.format("No department with id: %s exists", req.params("id")));
            } else {
                Map<String, Object> model = new HashMap<>();
                List<Employee> employees = departmentDao.getAllEmployeesByDepartment(departmentToFind.getId());
                String news = String.format("/departments/%s/news", req.params("id"));
                if (employees.size() == 0) {
                    String message = "I'm sorry, there are no employees currently.";
                    model.put("department", departmentToFind);
                    model.put("message", message);
                    model.put("departmentNews", news);
                    return gson.toJson(model);
                }else {
                    model.put("department", departmentToFind);
                    model.put("departmentEmployees", employees);
                    model.put("departmentNews", news);
                    return gson.toJson(model);
                }
            }
        });

        exception(ApiExceptions.class, (exception, req, res) -> {
            ApiExceptions err = exception;
            Map<String, Object> model = new HashMap<>();
            model.put("status", err.getStatusCode());
            model.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(model));
        });


        after((req, res) ->{
            res.type("application/json");
        });
    }
}


