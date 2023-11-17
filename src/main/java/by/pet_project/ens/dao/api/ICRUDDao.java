package by.pet_project.ens.dao.api;

import java.util.List;

public interface ICRUDDao <T>{
    List<T> get();
    T get(int id);
    T create(T item);
}
