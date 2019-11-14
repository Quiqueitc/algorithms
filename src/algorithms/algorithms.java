package algorithms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
/*
 * class algorithms
 * */

import java.io.*;
import java.net.URL;
import java.util.*;

public class algorithms implements Initializable {
    @FXML
    Button btnBFS, btnDFS, btnLF;
    @FXML
    TextArea txaA, txaC;
    @FXML
    TextField text;
    treeNario treeNario = new treeNario();
    String graph[][],BFS[][],DFS[][],AUX[][];
    File file;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBFS.setOnAction(handler);
        btnDFS.setOnAction(handler);
        btnLF.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            file = null;
            if (btnLF == event.getSource()) {
                file = openFile();

                /*String line, array[];
                int i = 0;
                try {
                    String txa;
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    line = br.readLine();
                    node root = treeNario.insertarRaiz(line);
                    txa = line + "\n";
                    line = br.readLine();
                    txa += line + "\n";
                    while (line != null) {
                        array = line.split(",");
                        treeNario.Insertar(root, array[1], array[0]);
                        line = br.readLine();
                        txa += line + "\n";
                        txaA.setText(txa);
                    }
                    br.close();
                } catch (Exception ex) {
                    System.out.println("Error   " + ex);
                }*/
                String line, array[];
                int i = 0;
                txaA.setStyle("-fx-text-fill: green");
                try {
                    String txa;
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    line = br.readLine();
                    ArrayList<String> lines = new ArrayList<>();

                    System.out.println("si");
                    //graph = new String[array.length][array.length];
                    txa="";
                    while (line != null) {
                     //   txa += line + "\n";
                        lines.add(line);
                        line = br.readLine();
                       // txaA.setText(txa);
                        i++;
                    }
                    br.close();
                    if (i>0) {
                        //array = lines.get(0).split(" ");
                        int r=0;
                        boolean ban=true;
                        while (r<i && ban){
                            array = lines.get(r).split(" ");
                            if (i != array.length)
                                ban=false;
                            r++;
                        }
                        if (ban){
                            graph=new String[i][i];
                            for (r = 0; r <graph.length ; r++) {
                                for (int c = 0; c <graph.length ; c++) {
                                    array=lines.get(r).split(" ");
                                    if(r==c)
                                        graph[r][c]=0+"";
                                    graph[r][c]=array[c];
                                    System.out.print(array[c]);
                                }
                                System.out.println();
                            }
                        }
                        else {
                            txaA.setText("Matriz no cuadrada GRAFO NO COMPLETO");
                            txaA.setStyle("-fx-text-fill: red");
                        }
                    }

                } catch (Exception e) {
                    System.out.println("Error " + e);
                }
                txaA.setText(show(graph));
                text.setVisible(true);
                text.setText("0");
            }
            if (event.getSource() == btnBFS) {

                if (graph!=null){
                    try {
                        BFS=null;
                        String FS = BFS(Integer.parseInt(text.getText()), graph);
                        txaC.setText(FS + "\n\n" + show(BFS) + "\n" + showTree(BFS));
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error "+e);
                    }
                }
                else
                {
                    txaC.setText("No selected file");
                    txaC.setStyle("-fx-text-fill: red");
                }

                /*if (treeNario.raiz != null) {
                    String m = treeNario.BFS((text.getText().isEmpty()) ? "A" : text.getText());
                    txaC.setText(m);
                    txaC.setStyle((m.equalsIgnoreCase("Not found") ? "-fx-text-fill: blue" : "-fx-text-fill: green"));
                } else {
                    txaC.setText("No selected file");
                    txaC.setStyle("-fx-text-fill: red");
                }*/
            }
            if (btnDFS == event.getSource()) {
                if (graph!=null){
                    try {
                        DFS=null;
                        DFS(Integer.parseInt(text.getText()), graph);
                        txaC.setText("\n\n" + show(DFS) + "\n" + showTree(DFS));
                    }
                    catch (Exception e)
                    {
                        System.out.println("Error "+e);
                    }
                }
                else
                {
                    txaC.setText("No selected file");
                    txaC.setStyle("-fx-text-fill: red");
                }

                /*if (treeNario.raiz != null) {
                    String m = treeNario.DFS((text.getText().isEmpty()) ? "A" : text.getText());
                    txaC.setText(m);
                    txaC.setStyle((m.equalsIgnoreCase("Not found") ? "-fx-text-fill: blue" : "-fx-text-fill: green"));
                } else {
                    txaC.setText("No selected file");
                    txaC.setStyle("-fx-text-fill: red");
                }*/
            }
        }
    };
    public String show(String[][] m){
        String message="";
        for (int r = 0; r <m.length ; r++) {
            for (int c = 0; c <m.length ; c++) {
                message+=m[r][c]+"    ";
            }
            message+="\n";
        }
        return message;
    }
    public String showTree(String[][] m){
        String message="";
        for (int r = 0; r <m.length ; r++) {
            for (int c = 0; c <m.length ; c++) {
                if(m[r][c].equalsIgnoreCase("1"))
                    message+=r+"  is dad of  "+c+" \n";
                //message+=m[r][c]+"    ";
            }
            message+="\n";
        }
        return message;
    }

    //int n;

    public String BFS(int root,String[][]m) {
        ArrayList<String> TABLE_A = new ArrayList<>();
        ArrayList<String> successors=null;
        Queue<String> OPEN = new LinkedList<>();
        OPEN.add(root+"");
        System.out.println("root  "+root);
        int n=0, before;
        while (!OPEN.isEmpty()) {
            n = Integer.parseInt(OPEN.poll());
            successors = seaChildren(n,m);
            BFS=AUX;
            if (successors != null) {
                for (int i = 0; i < successors.size(); i++) {
                    TABLE_A.add(successors.get(i));
                    //before = n;
                    OPEN.add(successors.get(i));
                }
            }
        }

        return root+ "  " + road(n, TABLE_A);
    }
    public ArrayList<String> seaChildren(int dad,String[][] m){
        ArrayList<String>childrens=new ArrayList<>();
        int c=0,z=0;
        for (c = 0; c <m.length ; c++) {

            if (!m[dad][c].equalsIgnoreCase("0"))
            {
                AUX=rowsZero(m,c,dad);
                System.out.println(show(AUX));
                childrens.add(c+"");
                z++;
            }
        }
        System.out.println("zeros view   "+z+ "    c   "+c);
        if (z==0) {
            System.out.println("si");
            return null;
        }
        else
            return childrens;
    }
    public ArrayList<String> seaChildrenDFSA(int dad,String[][] m){
        ArrayList<String>childrens=new ArrayList<>();
        int c=0,z=0;
        boolean ban=true;
       // for (c = 0; c <m.length ; c++) {
            if (!m[dad][c].equalsIgnoreCase("0"))
            {
                AUX=rowsZeroDFSA(m,c,dad);
                System.out.println(show(AUX));
                childrens.add(c+"");
                z++;
                //  ban=false;
            }
            // c++;
       // }
        //System.out.println("zeros view   "+z+ "    c   "+c);
        if (z>0) {
            System.out.println("si tiene hijos "+childrens);
            return childrens;
        }
        else
            return null;
    }

    public ArrayList<String> seaChildrenDFS(int dad,String[][] m){
        ArrayList<String>childrens=new ArrayList<>();
        int c=0,z=0;
        boolean ban=true;
        for (c = 0; c <m.length ; c++) {
            if (!m[dad][c].equalsIgnoreCase("0"))
            {
                AUX=rowsZeroDFS(m,c,dad);
                System.out.println(show(AUX));
                childrens.add(c+"");
                z++;
              //  ban=false;
            }
           // c++;
        }
        //System.out.println("zeros view   "+z+ "    c   "+c);
        if (z>0) {
            System.out.println("si tiene hijos "+childrens);
            return childrens;
        }
        else
            return null;
    }

    public String road(int end, ArrayList<String> TABLE_A) {
        String men = " ";
        int i = 0;
        while (i < TABLE_A.size()) {
            men += TABLE_A.get(i) + "  ";
            if (TABLE_A.get(i).equalsIgnoreCase(end+""))
                i = TABLE_A.size() + 2;
            i++;
        }
        return men;
    }

    public String[][]rowsZero(String[][] m, int c,int not){
        for (int r = 0; r <m.length ; r++) {
            if(r==not)
                m[r][c]=1+"";
            else
                m[r][c]=0+"";
        }
        m[c][not]=0+"";
        return m;
    }
    public String[][]rowsZeroDFS(String[][] m, int c,int not){
       /* for (int r = 0; r <m.length ; r++) {
            if(r==not)
                m[r][c]=1+"";
            else
                m[r][c]=0+"";
        }*/
        m[c][not]=(0)+"";
        return m;
    }
    public String[][]rowsZeroDFSA(String[][] m, int c,int not){
        for (int r = 0; r <m.length ; r++) {
            if(r==not)
                m[r][c]=1+"";
            else
                m[r][c]=0+"";
        }
        m[c][not]=(0)+"";
        return m;
    }

    public void DFS(int root,String[][]m) {
        ArrayList<String> successors;
        int n=0;

        System.out.println("toor  "+root+"m  "+m);
        //OPEN.add(root+"");
       // while (!OPEN.isEmpty()) {
            n =root;
            successors = seaChildrenDFS(n,m);
            DFS=AUX;
            System.out.println("succesors   "+successors);
            if (successors != null) {
                for (int i = 0; i <successors.size(); i++) {
                //    TABLE_A.add(successors.get(i));
                  //  before = n;
                    DFS(Integer.parseInt(successors.get(i)),DFS);
                    AUX=rowsZeroDFSA(m,Integer.parseInt(successors.get(i)),n);
                    DFS=AUX;
                    successors=seaChildrenDFS(n,DFS);
                    DFS=AUX;
                    System.out.println(show(AUX));
                    //OPEN.add(successors.get(i));
                }
            }
            else
            {
                //seaChildrenDFS(n,m);
                return ;
            }
      //  }
        //return n + "  " + road(n, TABLE_A);
    }

    public File openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("File");
        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );
        // Obtener la imagen seleccionada
        File file = fileChooser.showOpenDialog(null);
        return file;
    }
}
