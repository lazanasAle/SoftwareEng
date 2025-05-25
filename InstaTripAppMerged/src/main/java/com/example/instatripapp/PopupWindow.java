package com.example.instatripapp;


import javafx.scene.Node;

public interface PopupWindow<T> {
    public void createPopup(T element, Node anchor, long keySearch);
}
