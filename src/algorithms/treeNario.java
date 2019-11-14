package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;         //import cola
import java.util.Stack;         //import pila
/**
 * class treeNario
 * @author wicho
 */
public class treeNario {
    node raiz;
    int c = 0;
    public treeNario() {
    }
    public node insertarRaiz(String dato) {
        raiz = new node(dato, null);
        return raiz;
    }
    public void Insertar(node nodo, String dato, String padre) {
        int i = 0;
        boolean ban = true;
        node nuevo;
        if (nodo.info.equals(padre)) {
            nuevo = new node(dato, nodo);
            nodo.aumentarHijo(nuevo);
        } else {
            for (i = 0; i < nodo.nuChildren && ban; i++) {
                if (nodo.children[i].info.equals(padre)) {
                    nuevo = new node(dato, nodo.children[i]);
                    nodo.children[i].aumentarHijo(nuevo);
                    ban = false;
                } else if (ban)
                    Insertar(nodo.children[i], dato, padre);
            }
        }
    }
    public int depth(node node) {
        if (node.dad != null) {
            c++;
            depth(node.dad);
        }
        return c;
    }
    node n, before;
    public String BFS(String objective) {
        ArrayList<String> TABLE_A = new ArrayList<>();
        ArrayList<node> successors;
        Queue<node> OPEN = new LinkedList<>();
        OPEN.add(raiz);
        //node n, before;
        while (!OPEN.isEmpty()) {
            n = OPEN.poll();
            /*if (n.info.equals(objective)) {
                return raiz.info + "  " + road(n.info, TABLE_A);
            }*/
            successors = seaChildren(n);
            if (successors != null) {
                for (int i = 0; i < successors.size(); i++) {
                    TABLE_A.add(successors.get(i).info);
                    before = n;
                    OPEN.add(successors.get(i));
                }
            }
        }
        return raiz.info + "  " + road(n.info, TABLE_A);
    }

    public ArrayList<node> seaChildren(node node) {
        ArrayList<node> childrens = null;
        if (node.children != null) {
            childrens = new ArrayList<>();
            for (int i = 0; i < node.nuChildren; i++) {
                childrens.add(node.children[i]);
            }
        }
        return childrens;
    }

    public String road(String end, ArrayList<String> TABLE_A) {
        String men = " ";
        int i = 0;
        while (i < TABLE_A.size()) {
            men += TABLE_A.get(i) + "  ";
            if (TABLE_A.get(i).toString().equalsIgnoreCase(end))
                i = TABLE_A.size() + 2;
            i++;
        }
        return men;
    }

    public String DFS(String objective) {
        Stack<node> OPEN = new Stack<>();
        ArrayList<String> TABLE_A = new ArrayList<>();
        ArrayList<node> successors = null;
        //node n, before;
        OPEN.push(raiz);
        while (!OPEN.isEmpty()) {
            n = OPEN.pop();
            /*if (n.info.equalsIgnoreCase(objective)) {
                return raiz.info + "  " + road(n.info, TABLE_A);
            }*/
            if (depth(n) < depth((n.children != null) ? n.children[0] : n)) {
                successors = seaChildren(n);
            }
            if (successors == null) {
                TABLE_A.remove(n.info);
            }
            if (successors != null) {
                for (int i = 0; i < successors.size(); i++) {
                    TABLE_A.add(successors.get(i).info);
                    before = n;
                    OPEN.push(successors.get(i));
                }
            }
        }
        return raiz.info + "  " + road(n.info, TABLE_A);
    }
}
