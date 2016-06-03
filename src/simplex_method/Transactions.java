
package simplex_method;

import java.util.ArrayList;

public class Transactions {
     public static int minNumZMaxmize(ArrayList z)
  {
      int index=-1;
      double min=(double)z.get(0);
      for (int i =0; i <z.size()-1 && i<3; i++) {
          double var=(double)z.get(i);
          if(min>=var && var<0)
          {
              min=var;
              index=i;
          }
      }
    return index;  // index=smallest  number in Z
  }
     public static int minNumZManmize(ArrayList z)
  {
       int index=-1;
      double min=(double)z.get(0);
      for (int i =0; i <z.size()-1 && i<3; i++) {
          double var=(double)z.get(i);
          if(min >=var && var>0)
          {
              min=var;
              index=i;
          }
      }
    return index;  // index=smallest  number in Z
  }
     public static ArrayList pivotColumn(ArrayList tableau, int index)// index=smallest  number in Z
     {
         
         ArrayList pivotColumn=new ArrayList();
         for (int i = 0; i < tableau.size()-1; i++) {
             ArrayList row = (ArrayList)tableau.get(i); //  get every oldRow in the tableau represent arraylist
              pivotColumn.add(row.get(index));  
         }
         
       return pivotColumn;   
     
     }
      public static  int pivotRowIndex(ArrayList tableau ,ArrayList pivotColumn)
       {
       ArrayList newrhs=newRhS(tableau, pivotColumn);
           // newRHS(newrhs);
           int pivotRIndex=0;
           double div=(double)newrhs.get(0);
           for (int i = 0; i <newrhs.size(); i++) 
           {
             //  System.out.println(" "+newrhs.get(i));
            if( (double)newrhs.get(i) > 0&& div >=(double)newrhs.get(i))
             {
                div=(double)newrhs.get(i); //smallest remainder
                pivotRIndex=i;
              }
           }
           
             //  newrhs= (ArrayList)tableau.get(index);  //pivot oldRow 
           
       
       return pivotRIndex;
       }
       
       public static  double valMinrhs(ArrayList tableau, ArrayList pivotColumn)
       {
      
       ArrayList newrhs=newRhS(tableau, pivotColumn);
           // newRHS(newrhs);
           
           double div=(double)newrhs.get(0);
           for (int i = 0; i <newrhs.size(); i++) 
           {
              // System.out.println(" "+newrhs.get(i));
            if( (double)newrhs.get(i) > 0&& div >=(double)newrhs.get(i))
             {
                div=(double)newrhs.get(i); //smallest remainder
                
              }
           }
       return div;
       }
    public static ArrayList newRhS(ArrayList tableau, ArrayList pivotColumn) {
        ArrayList newrhs=new ArrayList();
        double div=0;
        for (int i = 0; i < tableau.size()-1; i++) {
            ArrayList row = (ArrayList)tableau.get(i);  //  get every oldRow in the tableau represent arraylist
            if((double)(row.get(row.size()-1))!=0)
                div=(double)(row.get(row.size()-1))/(double)(pivotColumn.get(i)); //divide R.H.S on pivot column
            newrhs.add(div);  // add remainder
        }
        return newrhs;
    }
      public static ArrayList pivotRow(ArrayList tableau,int pivotRIndex )
      {
      ArrayList pivotRow= (ArrayList)tableau.get(pivotRIndex);
      return pivotRow;
      }
       public static ArrayList newRowByPivotNumber(ArrayList pivotRow ,int columnindex)
       {
            double div=0;
           ArrayList newRow=new ArrayList();
           double pivotNumber=(double)pivotRow.get(columnindex);
           for (int i = 0; i < pivotRow.size()-1; i++) {
               if((double) pivotRow.get(i)!=0)
               {
                   div=(double) pivotRow.get(i)/pivotNumber;
                  newRow.add(div);
               }
               
               else{
               newRow.add(0.0);
               }
           }
   
       
         return newRow; 
       }
      
    
   public static ArrayList rebuildTableau(ArrayList tableau,ArrayList newRow,int columnIndex,int pivotRIndex,ArrayList pivotcolumn)
   {
       //double pivotNumber=(double)pivotRow.get(columnIndex);
             //tableau.remove(pivotRIndex); // delet old oldRow 
              // insert new oldRow 
                 double rhs=valMinrhs(tableau,pivotcolumn);
                 //System.out.println("---"+rhs);
                newRow.add(newRow.size(), rhs);
             ArrayList newTableau=new ArrayList();
              for (int j = 0; j < newRow.size(); j++) { 
              //  System.out.print("\t"+newRow.get(j));      
              }
         for (int i = 0; i < tableau.size(); i++) {  // i != pivotRIndex because it didn't need 
             
           ArrayList oldRow = (ArrayList)tableau.get(i);
           ArrayList newrow=new ArrayList();
             for (int j = 0; j < oldRow.size(); j++) { 
                 
                   double div= ((double) oldRow.get(j) - ((double)oldRow.get(columnIndex)*(double)newRow.get(j)));
                   //System.out.print("    "+div+" "+j);
                   newrow.add(div);
             } 
             newTableau.add( newrow); // insert new oldRow 
           
            }
         newTableau.remove(pivotRIndex);
         newTableau.add(pivotRIndex, newRow);   
          
      return  newTableau; 
  
   }
   
  
}
