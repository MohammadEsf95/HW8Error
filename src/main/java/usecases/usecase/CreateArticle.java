package usecases.usecase;

import entity.User;

public interface CreateArticle {
    void create(User user, String currentDate);
}
