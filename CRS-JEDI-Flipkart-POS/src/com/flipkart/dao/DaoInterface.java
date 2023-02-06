package com.flipkart.dao;

import java.util.List;
import java.util.Optional;

public interface DaoInterface<T> {
    T get(String id);
    List<T> getAll();
    int insert(T t);
    int update(String id, T t);
    int delete(T t);

}
