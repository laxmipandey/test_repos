package edu.gatech.cs2340.todo.model;

import java.util.Map;

public interface TodoDb {

    public Todo get(Integer id);

    public Map<Integer, Todo> list();

    /**
     * @return the database id of the newly created todo.
     */
    public Integer create(Todo todo);

    public void update(Integer id, Todo todo);

    public void delete(Integer id);
}
