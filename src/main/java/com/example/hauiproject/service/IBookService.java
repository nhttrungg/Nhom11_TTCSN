package com.example.hauiproject.service;

import java.util.List;

public interface IBookService<E> {
    void add(E e);
    List<E> findall();
    void edit(int index,E e);
    void  delete(int id);
    int findIndexById(int id);

    E show(int index);
}
