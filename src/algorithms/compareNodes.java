package algorithms;

import java.util.Comparator;


    public class compareNodes implements Comparator<nodo>{
        @Override
        public int compare(nodo nodo1, nodo nodo2) {
            if(Double.parseDouble(nodo1.getNodeValue())>Double.parseDouble(nodo2.getNodeValue())){
                return -1;
            }else if(Double.parseDouble(nodo2.getNodeValue())>Double.parseDouble(nodo1.getNodeValue())){
                return 0;
            }else{
                return 1;
            }
        }
    }
