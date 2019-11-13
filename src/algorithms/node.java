package algorithms;
/**
 *
 * @author wicho
 */
public class node {
    String info;
    int nuChildren;
    node dad;
    node children[];

    public node(String dato,node dad)
    {
        this.dad=dad;
        info=dato;
        nuChildren=0;
    }
    public node[] checkChildren()
    {
        int i;
        node childreT[]=null;
        childreT=new node[nuChildren+1];
        for(i=0;i<nuChildren;i++)
            childreT[i]=children[i];
        return childreT;
    }
    public void aumentarHijo(node S)
    {
        node childreT[]=checkChildren();
        childreT[nuChildren]=S;
        children=childreT;
        nuChildren=nuChildren+1;
    }
    public void mostrar()
    {
        System.out.println("{"+info+"}");
        //JOptionPane.showMessageDialog(null,"{"+info+"}");
    }
}
