package by.pet_project.ens.service.api;

import java.util.List;

public interface ICRUDService <T, S> {
    List<T> get();
    T get(int id);
    T create(S item);
}
