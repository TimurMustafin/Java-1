/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringandfile;


import java.io.*;
 

public class StringAndFile {

   public static void main(String[] args) {
      String S = "#java";
      StringBuilder keyWord = new StringBuilder(S);
      StringBuilder searchingWord = new StringBuilder("");
            
      String filePath = "/Users/timsonello/Desktop/AndroidDev/JAVA_1"
              + "/HomeWork/DZ_#6/StringAndFile/src"
              + "/stringandfile/StringAndFile.java";
      
      Test test = new Test;
      
      try {
          FileInputStream reader = new FileInputStream(filePath);
          int buffer = -1;
          int checkFindWord = 0;
          
          
          
          
          do{
            buffer = reader.read();
            
            allWords.append((char)buffer);
            if(((char)buffer == keyWord.charAt(0))) {
               checkFindWord = 1;
            }
            if(checkFindWord == 1) {
            searchingWord.append((char)(buffer));
            }
            if(searchingWord.length() == keyWord.length()) {
              String s1 = searchingWord.toString();
              String s2 = keyWord.toString();
              if(s1.equals(s2)) {/*метод equalsequals почему-то не срабатывает 
                  для объектов класса StringBuilder*/
                 System.out.println(searchingWord + "!!! I've found it!!!");
                 checkFindWord = 0;
                 searchingWord.setLength(0);
              }
              else {
                 checkFindWord = 0;
                 searchingWord.setLength(0);
              }
            }
          
            }while(buffer != -1);
      
      }catch(IOException e){e.printStackTrace();}
      //System.out.println(findWord + "is find!!!");
      
      //System.out.println(allWords);
            
     /* System.out.println(findWord);
      System.out.println();*/
   
      

     
   }
    
    
}


