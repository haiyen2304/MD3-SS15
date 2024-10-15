package konta.miniproject.controller;

import konta.miniproject.entity.Book;
import konta.miniproject.entity.Category;
import konta.miniproject.service.impl.BookServiceImpl;
import konta.miniproject.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoadBook", value = "/LoadBook")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class LoadBook extends HttpServlet {
    private BookServiceImpl bookService = new BookServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
//                showEditForm(request,response);
                break;
            case "delete":
//                deleteCategories(request,response);
                break;
            case "details":
//                showDetail(request,response);
                break;
            case "search":
//                searchCategories(request,response);
                break;
            default:
                listBooks(request,response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "save":
                saveBook(request,response);
                break;
            case "update":
//                updateCategory(request,response);
                break;
            default:
                listBooks(request,response);
                break;
        }
    }
    private void listBooks(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Books", bookService.findAll());
        List<Category> categories = new ArrayList<>();
        CategoryServiceImpl categoryService = new CategoryServiceImpl();
        categories = categoryService.findAll();
        request.setAttribute("Categories",categories);
        request.getRequestDispatcher("/views/book/book-list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Category> categories = new ArrayList<>();
        CategoryServiceImpl categoryService = new CategoryServiceImpl();
        categories = categoryService.findAll();
        request.setAttribute("Categories", categories);
        request.getRequestDispatcher("/views/book/book-form.jsp").forward(request,response);
    }
    private void saveBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        int categoryId = Integer.parseInt(request.getParameter("id"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String bookName = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        int totalPages = Integer.parseInt(request.getParameter("totalPages"));
        int yearCreated = Integer.parseInt(request.getParameter("yearCreated"));
        String author = request.getParameter("author");
        Part bookImagePart = request.getPart("bookImage");
        String status = request.getParameter("status");

        Boolean classStatus = true;

        if (status == null) classStatus = false;
        // Save the uploaded file
        String pathUpload = request.getServletContext().getRealPath("/upload/");
        File fileUpload = new File(pathUpload);
        if (!fileUpload.exists())
        {
            fileUpload.mkdir();
        }

        String fileName = bookImagePart.getSubmittedFileName();
        try
        {
            bookImagePart.write(fileUpload + File.separator + fileName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        Book book = new Book(1, categoryId, bookName, price, stock, totalPages, yearCreated, author, fileName, classStatus);

        bookService.add(book);

        response.sendRedirect("LoadBook");

    }

    public void destroy() {
    }
}