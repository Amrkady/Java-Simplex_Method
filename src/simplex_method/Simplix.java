
package simplex_method;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Simplix {
//public static double [] array;
//public static ArrayList newarray=new ArrayList();
    public static void main(String[] args) {
        
        
        ArrayList Xpram=zTransactions();
         
        int num=0;
        int count=0;
        String strn=JOptionPane.showInputDialog("how many equations without z");
        
          
        int numOfEquations=Integer.parseInt(strn);
        ArrayList tableau=new ArrayList();
        
         do{
            
          
           if(num==0)
           {
               count++;  
               String st2=JOptionPane.showInputDialog("Please Enter remiander equation such as ax1(+,-) bx2(+,-)...=RHS");
              String []arr1=new String[st2.length()];
              arr1=subString(st2);
              ArrayList Xpram1=new ArrayList();
                     Xpram1= perameterX(arr1);
                     
               Xpram1= signalOfXprameter(arr1,Xpram1);
                ArrayList n=transactionOnX(arr1);
                 
                
               int index= putZeroAtXPosition(n);
               if(index > -1){
                 //  System.out.println("index= "+index);
                Xpram1.add(index, 0.0);
               }
               Xpram1=completTableau(Xpram1,numOfEquations,count);
                
                 Xpram1.add(RHS(st2));
           //    System.out.println("array"+count+1 +""+Xpram1.size() );
               
               tableau.add(Xpram1);
           }
           else if(numOfEquations !=count)
           {
            JOptionPane.showMessageDialog(null,"Please Enter another equations");
            num=1;
           }
            num=JOptionPane.showConfirmDialog(null,"there are another equations ");
           }while(num==0);
         
        zZeros(Xpram.size(), Xpram);
         //int x=-1;
          
         // Xpram=completTableau(Xpram,numOfEquations,++count);
         // Xpram.add(0);
           tableau.add(Xpram);
         System.out.println();
          System.out.println("----------------------------------------");

            
        printTableau(tableau);
        System.out.println();
        String maxMin=JOptionPane.showInputDialog("Maximize Z press 1 or Minimize Z press 2");
        int x=Integer.parseInt(maxMin);
        if(x==1)
         maxmization(tableau);
        else if(x==2)
          minmization(tableau);
    }

    public static void printTableau(ArrayList tableau) {
        for (int i = 0; i < tableau.size(); i++) {
            ArrayList list=(ArrayList)tableau.get(i);
            if(i !=tableau.size()-1 )
                System.out.print("s"+(i+1));
            else
                System.out.print("z");  
            for (int j = 0; j < list.size(); j++) { 
                System.out.print("\t"+list.get(j));      
            }
            System.out.println();
        }
    }

    public static ArrayList zTransactions() throws HeadlessException {
       
        System.out.println("Please Enter Z equation such as z=ax1(+,-) bx2(+,-)...=0");
        String st=JOptionPane.showInputDialog("Please Enter Z equation such as ax1(+,-) bx2(+,-)...=z");
        String []arr=new String[st.length()];
        arr=subString(st);
        ArrayList x= transactionOnX(arr);
        PrintX(x);
        ArrayList Xpram=perameterX(arr);
        Xpram= signalOfXprameter(arr,Xpram);
        Xpram= zEquationPrameter(Xpram);
        return Xpram;
    }

    public static void zZeros(int numOfEquations, ArrayList Xpram) {
        for (int i =0; i < numOfEquations; i++) {
            Xpram.add(0.0);
            
        }
    }
    
    public static ArrayList zEquationPrameter(ArrayList list)
    {
        for (int i = 0; i < list.size(); i++) {
                double sum = (double)list.get(i) * -1;
                list.remove(i);
                list.add(i, sum);
        }
     return list;
    
    }
public static ArrayList completTableau(ArrayList xPram,int numOfEquations,int count)
  {
       switch(count)
       {
           case 1:{
               xPram.add(1.0);
               for (int i = 1; i <numOfEquations; i++)
               {
                   xPram.add(0.0); 
               
               }
              }
               break;
               case 2:
               {
               xPram.add(0.0);
               xPram.add(1.0);
               for (int i = 2; i <numOfEquations; i++)
               {
                   xPram.add(0.0); 
               }}
               break;
               case 3:{
               xPram.add(0.0);
               xPram.add(0.0);
               xPram.add(1.0);
               for (int i = 3; i <numOfEquations; i++)
               {
                   xPram.add(0.0); 
               }
          } 
               break;
               case 4:{
               xPram.add(0.0);
               xPram.add(0.0);
               xPram.add(0.0);
               xPram.add(1.0);
               for (int i = 4; i <numOfEquations; i++)
               {
                   xPram.add(0.0); 
               }
          } 
               case -1:{for (int i = 4; i <numOfEquations; i++)
               {
                   xPram.add(0.0); 
               }}break;
       }
       return xPram;
  }
public static int putZeroAtXPosition(ArrayList list)
  {
      int xZeroIndex=-1;
      int count =1;
      
      for (int i = 0; i < list.size(); i++)
       {
           //System.out.print("\t"+pivotcolumn.get(i));
          if( (int)list.get(i)!=i+1){
              xZeroIndex=i;
             break;
          }
         // count++;
          if( (int)list.get(list.size()-1)!=3 &&(int)list.size() >4){
              xZeroIndex=2;
             break;
          }
        }
  return xZeroIndex;
  }
    public static void PrintX(ArrayList x) {
       for (int i = 0; i <x.size(); i++) {
            if((int)x.get(i)!=0)
                System.out.print("\tx"+x.get(i));
        }
    }
     public static double RHS(String st)
       {
          st=st.trim();
         double rhs=0;
       for(int i = 0; i <st.length(); i++)
        {
             if (st.charAt(i) =='=') 
             { 
               rhs=Integer.parseInt(st.substring(i+1,st.length()));
              }   
         }
       return rhs;
       }
    public static ArrayList transactionOnX(String[] arr) {
       
        ArrayList x= numberX(arr);
        return x;
    }
    
    public static String [] subString(String s)
    {
        s=s.trim();
       String [] substring=new String [s.length()];
       int count=0,count1=0;
        if(s.charAt(0) !='-')
        { 
        for(int i = 0; i <s.length(); i++)
        {
             if (s.charAt(i) =='-' || s.charAt(i)=='+' ||s.charAt(i)=='=') 
             { 
               substring[count]=s.substring(count1,(i));
               count1=i;
               count++;
              }   
        }
        
    }
        else
        {
        for(int i = 1; i <s.length(); i++)
        {
             if (s.charAt(i) =='-' || s.charAt(i)=='+' ||s.charAt(i)=='=') 
             { 
               substring[count]=s.substring(count1,(i));
               count1=i;
               count++;
              }   
        }
        
        }
    return substring;
    }
    
    public static ArrayList numberX(String [] st)
    {
        // st array of string that contain on formula
         ArrayList numberx=new ArrayList();
         int count=0,d=0;
        for (int i = 0; i <st.length; i++) {
           if(st[i]!=null){
               d=st[i].length(); 
               for (int j = 0; j <d; j++)   
                  {
              if(st[i].charAt(j)=='x')
                 numberx.add(st[i].charAt(j+1)-'0');
                    }
              }
        }
    
    return numberx;
    }
    
    public static ArrayList perameterX(String [] st)
    {
        ArrayList numberx=new ArrayList();
         int count=0,d=0;
        for (int i = 0; i <st.length; i++) {
           if(st[i]!=null){
               d=st[i].length(); 
              // for (int j = 0; j <d-1; j++)  {      
                 if(Character.isDigit(st[i].charAt(0)))  // if it numbers
                  {
                         numberx.add((st[i].charAt(0)-'0')*1.0);  //add      
                    }
                      else if(st[i].charAt(0)=='x'||st[i].charAt(0)=='-'||st[i].charAt(0)=='+') // if it x
                       {
                          if((Character.isDigit(st[i].charAt(1))))
                               
                            numberx.add((st[i].charAt(1)-'0')*1.0);
                          else
                             numberx.add(1.0);
//                            count++;
                        //  break;
                       }
             // }
           }
      }
    return numberx;
    } 

    
   public static void maxmization(ArrayList tableau)
   {
       
   Transactions trans=new Transactions();
    ArrayList getZ=(ArrayList)tableau.get(tableau.size()-1); 
      int index=trans.minNumZMaxmize(getZ);
      ArrayList pivotcolumn=new ArrayList ();
     if(index >-1)
       pivotcolumn=trans.pivotColumn(tableau,index);
     
        int index1 =trans.pivotRowIndex(tableau,pivotcolumn);
        
          ArrayList pivotrow=trans.pivotRow(tableau,index1);
          
         ArrayList newrow= trans.newRowByPivotNumber(pivotrow,index);
         
          ArrayList newTableau=trans.rebuildTableau(tableau,newrow,index,index1,pivotcolumn);
         
            
           ArrayList list=(ArrayList)newTableau.get(newTableau.size()-1);
           int count=max(list);
            if(count >0)
            {
                     maxmization(newTableau);
            }
            else
                
                    System.out.println("z="+list.get(list.size()-1));
                  //  System.exit(0);
                  // break;
                
            
    
    
   }   
   public static void minmization(ArrayList tableau)
   {
       
   Transactions trans=new Transactions();
    ArrayList getZ=(ArrayList)tableau.get(tableau.size()-1); 
      int index=trans.minNumZManmize(getZ);
      ArrayList pivotcolumn=new ArrayList ();
     if(index >-1)
       pivotcolumn=trans.pivotColumn(tableau,index);
     
        int index1 =trans.pivotRowIndex(tableau,pivotcolumn);
        
          ArrayList pivotrow=trans.pivotRow(tableau,index1);
          
         ArrayList newrow= trans.newRowByPivotNumber(pivotrow,index);
         
          ArrayList newTableau=trans.rebuildTableau(tableau,newrow,index,index1,pivotcolumn);
         
            
           ArrayList list=(ArrayList)newTableau.get(newTableau.size()-1);
               int count=min(list);
                if(count > 0)
                     minmization(newTableau);
                else
                {
                    System.out.println("z="+list.get(list.size()-1));
                  //  System.exit(0);
                  // break;
                
            }
    
   }   
        public static int max(ArrayList list4)
        {
          int count =0;
           for (int j = 0; j < 3; j++) {
            if((double)list4.get(j) < 0.0)
                count ++;
           }
          
          
        return count ;
        }
        public static int min(ArrayList list4)
        {
          int count =0;
           for (int j = 0; j < 3; j++) {
            if((double)list4.get(j) > 0.0)
                count ++;
           }
          
          
        return count ;
        }
    public static ArrayList signalOfXprameter(String [] st,ArrayList Xpram)
    {
        double var=0;
       for (int i = 0; i <Xpram.size(); i++) {
           if(st[i]!=null){
              if((st[i].charAt(0))=='-')
              {
                  var=(double)Xpram.get(i)* -1;
                  Xpram.remove(i);
                  Xpram.add(i, var);
                 
               }
           }
    
       }
       return Xpram;
    }
   
}
