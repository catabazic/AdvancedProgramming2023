package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    public void create(String name);

    public List<T> getFindAllQuery();

    public T findById(int id);

    public T findByName(String name);

    public boolean existsName(String name);
}
