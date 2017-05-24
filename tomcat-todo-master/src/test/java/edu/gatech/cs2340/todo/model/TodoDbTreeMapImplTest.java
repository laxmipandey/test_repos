package edu.gatech.cs2340.todo.model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Iterator;
import java.util.Map;

@RunWith(JUnit4.class)
public class TodoDbTreeMapImplTest {

    @Test
    public void testGetAndCreate() {
        Todo todo = new Todo("Tattoo", "Get a tattoo.");
        TodoDb todoDb = new TodoDbTreeMapImpl();
        Integer id = todoDb.create(todo);
        Todo retrievedTodo = todoDb.get(id);
        assertEquals(todo, retrievedTodo);
    }

    @Test
    public void testList() {
        TodoDb todoDb = new TodoDbTreeMapImpl();
        Todo todo1 = new Todo("Tattoo", "Get a tattoo.");
        Integer id1 = todoDb.create(todo1);        
        Todo todo2 = new Todo("Groceries", "Buy groceries.");
        Integer id2 = todoDb.create(todo2);
        Map<Integer, Todo> todos = todoDb.list();

        // Make sure we get them back in order
        Iterator iter = todos.entrySet().iterator();
        Map.Entry<Integer, Todo> first = (Map.Entry<Integer, Todo>) iter.next();
        assertEquals("Wrong id for first todo.", id1, first.getKey());
        assertEquals("Wrong value for first todo.", todo1, first.getValue());

        Map.Entry<Integer, Todo> second = (Map.Entry<Integer, Todo>)iter.next();
        assertEquals("Wrong id for second todo.", id2, second.getKey());
        assertEquals("Wrong value for second todo.", todo2, second.getValue());
    }

    @Test
    public void testUpdate() {
        TodoDb todoDb = new TodoDbTreeMapImpl();
        Todo todo = new Todo("Tattoo", "Get a tattoo.");
        Integer id = todoDb.create(todo);
        Todo newTodo = new Todo("Remove tattoo",
                                "Remove ex's name from forehead.");
        todoDb.update(id, newTodo);
        Todo retrievedTodo = todoDb.get(id);
        assertEquals("Todo wasn't updated.", newTodo, retrievedTodo);
    }

    @Test
    public void testDelete() {
        TodoDb todoDb = new TodoDbTreeMapImpl();
        Todo todo1 = new Todo("Tattoo", "Get a tattoo.");
        Integer id1 = todoDb.create(todo1);        
        Todo todo2 = new Todo("Groceries", "Buy groceries.");
        Integer id2 = todoDb.create(todo2);
        
        todoDb.delete(id1);

        Map<Integer, Todo> todos = todoDb.list();
        assertEquals("Too many elements in db after delete.", 1, todos.size());
        Iterator iter = todos.entrySet().iterator();
        Map.Entry<Integer, Todo> first = (Map.Entry<Integer, Todo>) iter.next();
        assertNotEquals("Deleted todo id still in db.",
                        id1, first.getKey());
        assertNotEquals("Deleted todo value still in db.",
                        todo1, first.getValue());
        
    }

    
}


