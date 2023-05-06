package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    void create(String name);

    List<T> getFindAllQuery();

    T findById(int id);

    T findByName(String name);

    boolean existsName(String name);
}
