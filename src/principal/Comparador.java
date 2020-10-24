package principal;

import java.util.Comparator;

public class Comparador implements Comparator<Proceso>{
	     
     @Override
     public int compare(Proceso e1, Proceso e2){
        if(e1.getRafaga()>e2.getRafaga()){
            return 1;
        }else if(e1.getRafaga()>e2.getRafaga()){
            return 0;
        }else{
            return -1;
        }
    }
}
 