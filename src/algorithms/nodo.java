package algorithms;

import java.util.ArrayList;

public class nodo {
    String info;
    int nuChildren;
    ArrayList<nodo>children;
    nodo dad;
    public nodo(String dato,nodo dad)
    {
        this.dad=dad;
        info=dato;
        nuChildren=0;
    }
    public void aumentarHijo(nodo S)
    {
        children.add(S);
        nuChildren=nuChildren+1;
    }
}
