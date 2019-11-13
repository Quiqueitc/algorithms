package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author wicho
 */
public class treeNario {
      node raiz;


      public treeNario(){}


      public node insertarRaiz(String dato)
      {
          raiz=new node(dato,null);
          return raiz;
      }
      public void mostrar(node nodo)
      {
          int i=0;
          
          for(i=0;i<nodo.nuChildren;i++)
              nodo.children[i].mostrar();
          mostrar(nodo.children[i]);
      }
      node start;
      String men="";

      public void show(node nodo,boolean b){
          if(nodo!=null)
          {
            if (nodo.children!=null)
                      {
                         // start=nodo;
                          for(int i=0;i<nodo.nuChildren;i++)
                             {
                                 if (i==0)
                                 {
                                     start=nodo.children[i];
                                     System.out.println("start   "+start.info);
                                 }
                                 System.out.println("hijos de   "+nodo.info+ "tam "+nodo.nuChildren);
                              men +=nodo.children[i].info+"  ";
                               }  //lee
                          if(b){
                              for (int j=0;j<nodo.nuChildren;j++)
                              {
                                  if (j==0)
                                  {
                                      start=nodo.children[j];
                                      System.out.println("start   "+start.info);
                                  }
                                  System.out.println("hijos del actual   "+nodo.nuChildren);
                                  if (j==nodo.nuChildren-1) {
                                      //poner lectura aquí
                                      for(int k=0;k<nodo.children[j].nuChildren;k++)
                                      {
                                          System.out.println(" vex  "+k);
                                          men +=nodo.children[j].children[k].info+"  ";
                                      }  //lee
                                      //show(nodo.hijos[j], true);
                                      System.out.println("start es  "+start.info);
                                      show(start, true);
                                  }
                                  else
                                      show(nodo.children[j],false);
                              }
                              men+="\n";
                          }
                          else
                              return;
                      }
            else
                return;
          }
          else
              return ;
      }
    public void Insertar(node  nodo, String dato, String padre)
    {
        int i=0;
        boolean ban=true;
        node nuevo;
        //node nuevo=new node(dato);
        if(nodo.info.equals(padre))
        {
            nuevo=new node(dato,nodo);
            nodo.aumentarHijo(nuevo);
            System.out.println("Profundidad    "+nodo.info+"  "+profundidad(nodo));
            c=0;
        }
        else
        {
            for(i=0;i<nodo.nuChildren && ban;i++)
            {
                System.out.println("Profundidad    "+nodo.children[i].info+"  "+profundidad(nodo.children[i]));
                c=0;
                if(nodo.children[i].info.equals(padre))
                        {
                            nuevo=new node(dato,nodo.children[i]);
                            nodo.children[i].aumentarHijo(nuevo);
                            ban=false;
                            System.out.println("Profundidad    "+nodo.children[i].info+"  "+profundidad(nodo.children[i]));
                            c=0;
                        }
                else
                    if(ban)
                        Insertar(nodo.children[i],dato,padre);
            }
        }
    }
    int c=0;
    public int profundidad(node node){
          if (node.dad!=null) {
                c++;
                profundidad(node.dad);
          }
          return c;
    }
    public void BFS(String objective){
        ArrayList<String>TABLE_A=new ArrayList<>();
        Queue<node> OPEN=new LinkedList<>();
        OPEN.add(raiz);
        node n;

        while(!OPEN.isEmpty()){
            n=OPEN.poll();
            System.out.println(n.info);
            if(n.info.equals(objective)){
                round(n.info,TABLE_A);
            }

        }

    }
    public String round(String end, ArrayList<String> TABLE_A){
        String men="";
        for (int i = 0; i <TABLE_A.size() ; i++) {
            if (TABLE_A.get(i).equals(end))
                men+=end+" ";
        }
        return men;
    }
    public void DFS(){

    }
}
