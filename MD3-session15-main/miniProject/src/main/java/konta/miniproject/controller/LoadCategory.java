package konta.miniproject.controller;

import konta.miniproject.entity.Category;
import konta.miniproject.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoadCategory", value = "/LoadCategory")
public class LoadCategory extends HttpServlet {
    private CategoryServiceImpl categoryService = new CategoryServiceImpl();

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
                showEditForm(request,response);
                break;
            case "delete":
                deleteCategories(request,response);
                break;
            case "details":
                showDetail(request,response);
                break;
            case "search":
                searchCategories(request,response);
                break;
            default:
                listCategories(request,response);
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
                saveCategory(request,response);
                break;
            case "update":
                updateCategory(request,response);
                break;
            default:
                listCategories(request,response);
                break;
        }
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("Categories", categoryService.findAll());
        request.getRequestDispatcher("/views/category/category-list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/views/category/category-form.jsp").forward(request,response);
    }

    private void saveCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        int categoryId = Integer.parseInt(request.getParameter("id"));
        String categoryName = request.getParameter("categoryName");
        String status = request.getParameter("status");

        Boolean classStatus = true;

        if (status == null) classStatus = false;

        Category Categories = new Category(1,categoryName, classStatus);

        categoryService.add(Categories);

        response.sendRedirect("LoadCategory");

    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        Category existCategory = categoryService.findById(categoryId);
        System.out.println(existCategory.getId());
        request.setAttribute("category", existCategory);
        request.getRequestDispatcher("/views/category/category-form.jsp").forward(request,response);
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        String categoryName = request.getParameter("categoryName");
        String status = request.getParameter("status");

        Boolean accountClass = true;

        if (status == null) accountClass = false;

        Category Categories = new Category(categoryId, categoryName, accountClass);
        categoryService.edit(Categories);

        response.sendRedirect("LoadCategory");
    }

    private void deleteCategories(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        boolean check = categoryService.delete(categoryId);
//        System.out.println(CategoriesService.delete(categoryId));
        if(!check){
            String errorDelete = "Can't delete Category because it has books";
            request.setAttribute("errorDelete",errorDelete);
        }
//        response.sendRedirect("LoadCategory");
        request.setAttribute("Categories", categoryService.findAll());
        request.getRequestDispatcher("/views/category/category-list.jsp").forward(request,response);
    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        Category Categories = categoryService.findById(categoryId);

        request.setAttribute("Categories", Categories);
        request.getRequestDispatcher("/views/category/category-details.jsp").forward(request, response);
    }

    private void searchCategories(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String searchQuery = request.getParameter("searchQuery");
        List<Category> searchResults = categoryService.findByName(searchQuery);

        request.setAttribute("Categories", searchResults);
        request.getRequestDispatcher("/views/category/category-list.jsp").forward(request, response);
    }

    public void destroy() {
    }
}