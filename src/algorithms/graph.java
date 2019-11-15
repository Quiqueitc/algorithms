package algorithms;

public class graph {
    nodo root;
    public graph() {
    }
    public nodo insertRoot(String dato) {
        root = new nodo(dato, null);
        return root;
    }
   /* public void insert(nodo nodo, String dato, String padre) {
        int i = 0;
        boolean ban = true;
        nodo nuevo;
        if (nodo.info.equals(padre)) {
            nuevo = new nodo(dato, nodo);
            nodo.aumentarHijo(nuevo);
        } else {
            for (i = 0; i < nodo.nuChildren && ban; i++) {
                if (nodo.children.get(i).info.equals(padre)) {
                    nuevo = new nodo(dato, nodo.children.get(i));
                    nodo.children.get(i).aumentarHijo(nuevo);
                    ban = false;
                } else if (ban)
                    insert(nodo.children.get(i), dato, padre);
            }
        }
    }*/
    nodo nodo=null;
   /* public nodo search(nodo search,nodo root){
        int i=0;
        boolean ban=true;
        nodo nuevo;
        if(root.info.equalsIgnoreCase(nodo.info))
        {

        }
        else
        {

        }
        return nodo;
    }*/
}
