package algorithms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
/*
 * class algorithms
 * */

import java.io.*;
import java.net.URL;
import java.util.*;

public class algorithms implements Initializable {
    @FXML
    Button btnBFS, btnDFS, btnLF, btnUCS;
    @FXML
    TextArea txaA, txaC;
    @FXML
    TextField text;
    @FXML
    Label lbl;
    String graph[][], BFS[][], DFS[][], AUX[][], UCS[][];
    File file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnBFS.setOnAction(handler);
        btnDFS.setOnAction(handler);
        btnLF.setOnAction(handler);
        btnUCS.setOnAction(handler);
    }

    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Alert alert;
            file = null;
            if (btnLF == event.getSource()) {
                file = openFile();
                String line, array[];
                lbl.setText("Filename:  " + file.getName());
                int i = 0;
                txaA.setStyle("-fx-text-fill: green");
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    line = br.readLine();
                    ArrayList<String> lines = new ArrayList<>();
                    while (line != null) {
                        lines.add(line);
                        line = br.readLine();
                        i++;
                    }
                    br.close();
                    if (i > 0) {
                        int r = 0;
                        boolean ban = true;
                        while (r < i && ban) {
                            array = lines.get(r).split(" ");
                            if (i != array.length)
                                ban = false;
                            r++;
                        }
                        if (ban) {
                            graph = new String[i][i];
                            for (r = 0; r < graph.length; r++) {
                                for (int c = 0; c < graph.length; c++) {
                                    array = lines.get(r).split(" ");
                                    if (r == c)
                                        graph[r][c] = 0 + "";
                                    graph[r][c] = array[c];
                                }
                            }
                        } else {
                            txaA.setText("Matriz no cuadrada GRAFO NO COMPLETO");
                            txaA.setStyle("-fx-text-fill: red");
                        }
                    }
                } catch (Exception e) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(e + "");
                    alert.setTitle("Error");
                    alert.show();
                    System.out.println("Error " + e);
                }
                txaA.setText(show(graph));
                text.setVisible(true);
                text.setText("0");
            }
            if (event.getSource() == btnBFS) {
                if (graph != null) {
                    try {
                        BFS = null;
                        txaC.setText(BFS(Integer.parseInt(text.getText()), graph));
                    } catch (Exception e) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(e + "");
                        alert.setTitle("Error");
                        alert.show();
                        System.out.println("Error " + e);
                    }
                } else {
                    txaC.setText("No selected file");
                    txaC.setStyle("-fx-text-fill: red");
                }
            }
            if (btnDFS == event.getSource()) {
                if (graph != null) {
                    try {
                        DFS = null;
                        DFS(Integer.parseInt(text.getText()), graph);
                        txaC.setText(show(DFS) + "\n" + showTree(DFS));
                    } catch (Exception e) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(e + "");
                        alert.setTitle("Error");
                        alert.show();
                        System.out.println("Error " + e);
                    }
                } else {
                    txaC.setText("No selected file");
                    txaC.setStyle("-fx-text-fill: red");
                }
            }
            if (btnUCS == event.getSource()) {
                if (graph != null) {
                    try {
                        UCS = null;
                        txaC.setText(UCS(new nodo(Integer.parseInt(text.getText()) + "", 0 + ""), graph, objective() + ""));
                    } catch (Exception e) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(e + "");
                        alert.setTitle("Error");
                        alert.show();
                        System.out.println("Error " + e);

                    }
                } else {
                    txaC.setText("No selected file");
                    txaC.setStyle("-fx-text-fill: red");
                }
            }
        }
    };

    public int objective() {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Select a objective");
        dialog.setHeaderText("Search the shortest route");
        dialog.setContentText("Objective:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return Integer.parseInt(result.get());
        }

        return 0;
        //        result.ifPresent(name -> System.out.println("Your name: " + name));
    }

    public String show(String[][] m) {
        String message = "";
        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m.length; c++) {
                message += m[r][c] + "    ";
            }
            message += "\n";
        }
        return message;
    }

    public String showTree(String[][] m) {
        String message = "";
        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m.length; c++) {
                if (m[r][c].equalsIgnoreCase("1"))
                    message += r + "  is dad of  " + c + " \n";
            }
            message += "\n";
        }
        return message;
    }

    public String BFS(int root, String[][] m) {
        ArrayList<String> TABLE_A = new ArrayList<>();
        ArrayList<String> successors = null;
        Queue<String> OPEN = new LinkedList<>();
        OPEN.add(root + "");
        int n = 0;
        while (!OPEN.isEmpty()) {
            n = Integer.parseInt(OPEN.poll());
            successors = seaChildren(n, m);
            System.out.println("Successsors  " + successors);
            BFS = AUX;
            if (successors != null) {
                for (int i = 0; i < successors.size(); i++) {
                    TABLE_A.add(successors.get(i));
                    OPEN.add(successors.get(i));
                }
            }
        }
        return show(BFS) + "\n" + showTree(BFS);
    }

    public ArrayList<String> seaChildren(int dad, String[][] m) {
        ArrayList<String> childrens = new ArrayList<>();
        int c, z = 0;
        for (c = 0; c < m.length; c++) {
            if (!m[dad][c].equalsIgnoreCase("0")) {
                AUX = rowsZero(m, c, dad);
                childrens.add(c + "");
                z++;
            }
        }
        if (z > 0)
            return childrens;
        else
            return null;
    }

    public ArrayList<String> seaChildrenDFS(int dad, String[][] m) {
        ArrayList<String> childrens = new ArrayList<>();
        int c, z = 0;
        for (c = 0; c < m.length; c++) {
            if (!m[dad][c].equalsIgnoreCase("0")) {
                AUX = rowsZeroDFS(m, c, dad);
                childrens.add(c + "");
                z++;
            }
        }
        if (z > 0)
            return childrens;
        else
            return null;
    }

    public String[][] rowsZero(String[][] m, int c, int not) {
        for (int r = 0; r < m.length; r++) {
            if (r == not)
                m[r][c] = 1 + "";
            else
                m[r][c] = 0 + "";
        }
        m[c][not] = 0 + "";
        return m;
    }

    public String[][] rowsZeroDFS(String[][] m, int c, int not) {
        m[c][not] = (0) + "";
        return m;
    }

    public String[][] rowsZeroDFSA(String[][] m, int c, int not) {
        for (int r = 0; r < m.length; r++) {
            if (r == not)
                m[r][c] = 1 + "";
            else
                m[r][c] = 0 + "";
        }
        m[c][not] = (0) + "";
        return m;
    }

    public void DFS(int root, String[][] m) {
        ArrayList<String> successors;
        int n = 0;
        n = root;
        successors = seaChildrenDFS(n, m);
        DFS = AUX;
        if (successors != null) {
            for (int i = 0; i < successors.size(); i++) {
                DFS(Integer.parseInt(successors.get(i)), DFS);
                AUX = rowsZeroDFSA(m, Integer.parseInt(successors.get(i)), n);
                DFS = AUX;
                successors = seaChildrenDFS(n, DFS);
                DFS = AUX;
            }
        } else {
            return;
        }
    }

    int cost[];

    public int[] initCost(int tam) {
        int[] m = new int[tam];
        for (int i = 0; i < tam; i++) {
            m[i] = 0;
        }
        return m;
    }

    public String UCS(nodo root, String[][] m, String objective) {
        ArrayList<String> TABLE_A = new ArrayList<>();
        ArrayList<nodo> successors;
        cost = initCost(m.length);
        ArrayList<nodo> OPENlist = new ArrayList<>();
        String h = "";
        OPENlist.add(root);
        nodo n;
        while (!OPENlist.isEmpty()) {
            h += "Order List:  " + showList(OPENlist) + "\n";
            n = OPENlist.get(OPENlist.size() - 1);
            OPENlist.remove(OPENlist.size() - 1); //con estas dos lineas hacemos el poll de la cola

            if (n.name.equals(objective)) {
                return "Found  "+objective+"\nCost: "+n.getNodeValue()+"\n"+h + "\n" + root.name + "  " + road(n.name, TABLE_A) + "\n" + show(m);
            }
            successors = seaChildrenUCS(n, m); //regresa lista NO ordenada
            h += "node  name:  " + n.getNodeName() + "  value:  " + n.getNodeValue() + "  successors:   " + showList(successors) + "\n";
            UCS = AUX;
            m = UCS;
            if (successors != null) {
                for (int i = 0; i < successors.size(); i++) //meto a la otra lista cada uno de los valores q de S
                {
                    TABLE_A.add(successors.get(i).getNodeName());
                    OPENlist.add(successors.get(i));
                }
                OPENlist = sortList(OPENlist);
            }
        }
        return null;
    }

    public String showList(ArrayList<nodo> list) {
        String na = "[", va = "[";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                na += list.get(i).getNodeName() + (((i == list.size() - 1)) ? "" : " , ");
                va += list.get(i).getNodeValue() + (((i == list.size() - 1)) ? "" : " , ");
            }
        }
        na += "]";
        va += "]";
        return "\n" + na + "\n" + va;

    }

    public ArrayList<nodo> seaChildrenUCS(nodo node, String[][] m) {
        ArrayList<nodo> childrens = new ArrayList<>();
        int c, z = 0;
        for (c = 0; c < m.length; c++) {
            if (!m[Integer.parseInt(node.name)][c].equalsIgnoreCase("0") && !m[Integer.parseInt(node.name)][c].equalsIgnoreCase("v") && !m[Integer.parseInt(node.name)][c].equalsIgnoreCase("c")) //si encuentra algo diferente a 0 entonces es poque hay camino y tiene un costo, una vez visitado se cambia [r][c]="V" y [c][r]="V"
            {
                cost[c] = cost[Integer.parseInt(node.getNodeName())] + Integer.parseInt(m[Integer.parseInt(node.name)][c]);
                childrens.add(new nodo(c + "", cost[c] + ""));
                m[Integer.parseInt(node.name)][c] = "v"; // cambia m[r][c]="v"
                m[c][Integer.parseInt(node.name)] = "c"; // cambia m[c][r]="c"
                AUX = m;
                z++;
            }
        }
        if (z > 0) {
            return childrens;
        } else
            return null;
    }

    public ArrayList<nodo> sortList(ArrayList<nodo> list) {
        Collections.sort(list, new compareNodes());
        return list;
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
