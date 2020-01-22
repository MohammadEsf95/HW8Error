package repository;

import entity.User;
import usecases.impl.*;
import usecases.impl.adminImpl.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        StarterUsecaseImpl starterUsecase = new StarterUsecaseImpl();
        starterUsecase.start();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Login, Sign Up, Show Articles: ");
        String command = null;
        User user = null;
        String date = date();
        command = scanner.nextLine();
        boolean isAdmin = false;

        while (true){

            if(command.equalsIgnoreCase("login")){
                LoginImpl login = new LoginImpl();
                user = login.login();
                isAdmin = new AdminDefine().isAdmin(user);
                if(user != null && isAdmin){
                    System.out.println("Change user role, create category, create tag," +
                            "delete article, publish, unpublish, show all articles");

                    command = scanner.nextLine();

                    if(command.equalsIgnoreCase("change user role")){
                        ChangeUserRoleImpl changeUserRole = new ChangeUserRoleImpl();
                        changeUserRole.change();
                    }

                    if(command.equalsIgnoreCase("create category")){
                        CreateCategoryImpl createCategory = new CreateCategoryImpl();
                        createCategory.createCategory();
                    }

                    if(command.equalsIgnoreCase("create tag")){
                        CreateTagImpl createTag = new CreateTagImpl();
                        createTag.createTag();
                    }

                    if(command.equalsIgnoreCase("delete article")){
                        DeleteArticleImpl deleteArticle = new DeleteArticleImpl();
                        System.out.println("Enter article id: ");
                        Long id = Long.parseLong(scanner.nextLine());
                        deleteArticle.deleteArticle(id);
                    }
                    if(command.equalsIgnoreCase("publish")){
                        PublishImpl publish = new PublishImpl();
                        System.out.println("Enter article id: ");
                        Long id = Long.parseLong(scanner.nextLine());
                        publish.publish(id);
                    }
                    if(command.equalsIgnoreCase("unpublish")){
                        UnpublishImpl unpublish = new UnpublishImpl();
                        System.out.println("Enter article id: ");
                        Long id = Long.parseLong(scanner.nextLine());
                        unpublish.publish(id);
                    }
                    if(command.equalsIgnoreCase("Show all articles")){
                        ShowAllArticlesImpl showAllArticles = new ShowAllArticlesImpl();
                        showAllArticles.showAllArticles();
                    }
                }
                if(user != null && !isAdmin){
                    System.out.println("Login successful :*");
                    System.out.println("New article, edit article, change password, view articles, remove: ");
                    command = scanner.nextLine();
                    if(command.equalsIgnoreCase("new article")){
                       CreateArticleImpl createArticle = new CreateArticleImpl();
                       createArticle.create(user,date);
                    }else if (command.equalsIgnoreCase("edit article")){
                        EditArticleTextImpl editArticleText = new EditArticleTextImpl();
                        System.out.println("Enter article id: ");
                        Long id = scanner.nextLong();
                        editArticleText.edit(id,date);
                    }else if(command.equalsIgnoreCase("change password")){
                        ChangePasswordImpl changePassword = new ChangePasswordImpl();
                        changePassword.change(user.getUsername());
                    }else if(command.equalsIgnoreCase("view articles")){
                        ViewAllArticlesImpl viewAllArticles = new ViewAllArticlesImpl();
                        viewAllArticles.showAllArticles();
                    }else if (command.equalsIgnoreCase("remove")){
                        UnpublishImpl unpublish = new UnpublishImpl();
                        System.out.println("Article id: ");
                        Long artileId = scanner.nextLong();
                        unpublish.publish(artileId);
                    }
                }

            }else if(command.equalsIgnoreCase("sign up")){
                SignUpImpl signUp = new SignUpImpl();
                signUp.signUp();
                AddAddressImpl addAddress = new AddAddressImpl();
                addAddress.add();
            }else if(command.equalsIgnoreCase("show articles")){
                ViewAllArticlesImpl viewAllArticles = new ViewAllArticlesImpl();
                viewAllArticles.showAllArticles();
            }
        }
    }
    public static String date(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime localDate = LocalDateTime.now();
        return date.format(localDate);
    }
}
