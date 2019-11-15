package algorithms;

import java.util.ArrayList;

public class nodo {
    public nodo() {
    }

    String name;

    public nodo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    String value;
    public String getNodeName() {
        return name;
    }

    public void setNodeName(String nodeName) {
        this.name = nodeName;
    }

    public String getNodeValue() {
        return value;
    }

    public void setNodeValue(String nodeValue) {
        this.value = nodeValue;
    }



}
