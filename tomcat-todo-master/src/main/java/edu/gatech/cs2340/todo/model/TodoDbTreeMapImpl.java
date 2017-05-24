package edu.gatech.cs2340.todo.model;

import java.util.Map;
import java.util.TreeMap;

public class TodoDbTreeMapImpl implements TodoDb {

    private TreeMap<Integer, Todo> todos = new TreeMap<Integer, Todo>();

    public Todo get(Integer id) {
        return todos.get(id);
    }

    public Map<Integer, Todo> list() {
        return todos;
    }

    public Integer create(Todo todo) {
        Integer newId = todos.size();
        todos.put(newId, todo);
        return newId;
    }

    public void update(Integer id, Todo todo) {
        todos.put(id, todo);
    }

    public void delete(Integer id) {
        todos.remove(id);
    }
}
