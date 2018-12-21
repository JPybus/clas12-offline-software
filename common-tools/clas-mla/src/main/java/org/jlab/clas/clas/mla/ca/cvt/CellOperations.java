/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jlab.clas.clas.mla.ca.cvt;

import java.util.List;
import org.jlab.clas.clas.mla.ca.ACell;

/**
 *
 * @author ziegler
 */
public class CellOperations {

    
    
    public void findNeighbors(List<Cell> allCells) {
        for(int ic=0;ic<allCells.size();ic++){
            
            for(int jc=ic+1;jc<allCells.size();jc++){
      		  
                if( allCells.get(ic).getNeighbors()!=null &&
                        allCells.get(ic).getNeighbors().contains(allCells.get(jc))) {
                    continue;
                }
                if( allCells.get(ic).getSourceNode().equals(allCells.get(jc).getSinkNode()) ){ 
                    if(allCells.get(jc).passNeighboringCondition(allCells.get(ic))) {
                        allCells.get(ic).addNeighbor(allCells.get(jc));
                    }
                }
            }
        }
    }
    
    public void evolve(List<Cell> allCells, int N) {
        int[] states = new int[allCells.size()];
        // evolve
        for( int i=0; i<N; i++){ // epochs   
            // find the state for each cell
            int j=0;
            for( Cell c : allCells ){
                int max = 0;
                if(c.getNeighbors()==null)
                    continue;
                for( ACell n : c.getNeighbors() ){
                    if( n.getState() > max ) {
                        max = n.getState();
                    } 
                }
                states[j] =  1 + max ;
                j++;
            }
            // update all cell states at once
            for( j=0;j<allCells.size();j++) {
                allCells.get(j).setState(states[j]);
            }
        }
    }


    

}
