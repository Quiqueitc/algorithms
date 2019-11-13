package algorithms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class algorithms implements Initializable {
    @FXML
    Button btnA;
    @FXML
    TextArea txaA;
    treeNario treeNario= new treeNario();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnA.setOnAction(handler);
    }
    EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource()==btnA)
            {
                String line, array[];
                int i=0;
                try{
                    BufferedReader br= new BufferedReader(new FileReader("C:/archi/DataForGraph.txt"));
                    line=br.readLine();
                    int dad,children;
                    node root=treeNario.insertarRaiz(line);
                    System.out.println("line   "+line);
                    line=br.readLine();
                    System.out.println("line   "+line);
                    while(line!=null)
                    {
                        array=line.split(",");

                        //dad=Integer.parseInt(array[0]);
                        //children=Integer.parseInt(array[1]);
                        treeNario.Insertar(root, array[1], array[0]);
                        System.out.println("Line   "+line);
                        line=br.readLine();
                    }
                    // JOptionPane.showMessageDialog(null,ob.ar.in_orden(ob.ar.raiz));
                    br.close();
                   /* treeNario.start=root;
                    treeNario.show(root,(root.nuChildren>=1)?true:false);;
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(treeNario.men);
                    alert.show();*/
                    //da=Integer.parseInt(JOptionPane.showInputDialog("Dame el dato a buscar: "));
                    //  dire=ob.ar.buscar(da);
                    //JOptionPane.showMessageDialog(null,"Estado "+);
                    // if(ob.ar.buscar(da))

                    // JOptionPane.showMessageDialog(null,"Son " +ob.ar.ancestros(da));
                    /*  if(ob.ar.buscar(da)!=null)*/
                    //JOptionPane.showMessageDialog(null,"Sucesores son " +ob.ar.sucesores(da));
                  /*  if(!ob.ar.arbol_vacio())
                    {
                        da=Integer.parseInt(JOptionPane.showInputDialog("Número a eliminar:"));
                        if(ob.ar.eliminar(da))
                            JOptionPane.showMessageDialog(null, "Se eliminó el nodo");
                        else
                            JOptionPane.showMessageDialog(null, "No se eliminó el nodo");
                        System.out.println(ob.ar.in_orden(ob.ar.raiz));
                    }
                    else
                        JOptionPane.showMessageDialog(null,"No hay datos");*/
                    //  JOptionPane.showMessageDialog(null,"Nivel " +ob.ar.nivel(da));
                }
                catch(NumberFormatException ex)
                {
                    System.out.println("Error   "+ex);
                } catch (FileNotFoundException ex) {
                    //JOptionPane.showMessageDialog(null,"Archivo no encontrado");
                } catch (IOException ex) {
                    System.out.println("Error   "+ex);
                }
            }
        }
    };

}
