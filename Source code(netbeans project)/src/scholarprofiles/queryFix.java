/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scholarprofiles;

/**
 *
 * @author babs
 */
public class queryFix {
    String[] finalSearch;
   public queryFix(String search){
       String[] temp = search.split(" ");
       finalSearch=new String[temp.length];
       for(int i=0; i<temp.length;i++){
           finalSearch[i]=temp[i];
           
        }
   } 
   
   String[] getString(){
       return finalSearch;
   }
}
