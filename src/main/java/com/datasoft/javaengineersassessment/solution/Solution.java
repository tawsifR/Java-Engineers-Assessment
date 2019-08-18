package com.datasoft.javaengineersassessment.solution;

import com.datasoft.javaengineersassessment.utils.IO;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Solution implements Runnable{
	
	
	/**
	 * Application entry to your solution
	 *
	 * @see Thread#run()
	 */
	@Override
	public void run() {
                System.out.println("Number of test Case: ");
                int testCase=Integer.parseInt(IO.readLine());
                int tableNumber;
                int objectNumber;
                String[] objects;
                String input;
                JSONObject jObject;
                JSONObject jObjecta;
                Object keyTest;
                String tempString;
                int counter;
                String col="";
                String row="";
                boolean b= true;
                Map<String,Map<String,String>> objectMap=new HashMap<String,Map<String,String>>();
                Map<String,String> map = new HashMap<String,String>();
                JSONParser parser = new JSONParser();
                for(int i=testCase;i>=1;i--){
                    counter=0;
                    System.out.println("Number of tables and number of JSON data Object: ");
                    String[] temp=IO.readLine().split(" ");
                    tableNumber=Integer.parseInt(temp[0]);
                    objectNumber=Integer.parseInt(temp[1]);
                    System.out.println("Name of the objects: ");
                    objects=IO.readLine().split(" ");
                    for(int k=objectNumber;k>=1;k--){
                        counter+=1;
                        System.out.println("Input Data: ");
                        input=IO.readLine();
                        try{
                            jObject= (JSONObject) parser.parse(input);
                            Iterator<?> keys=jObject.keySet().iterator(); 
                            while(keys.hasNext()){
                                String key = (String)keys.next();
                                keyTest=jObject.get(key);
                                if(keyTest instanceof JSONObject){
                                    tempString=(String)jObject.get(key).toString();
                                    jObjecta= (JSONObject) parser.parse(tempString);
                                    Iterator<?> keyTestSet= jObjecta.keySet().iterator();
                                    while(keyTestSet.hasNext()){
                                        String st = (String)keyTestSet.next();
                                        map.put(st,(String)jObjecta.get(st).toString());
                                    }
                                }else{
                                    map.put(key,(String)jObject.get(key).toString());
                                }
                            }
                            String temporaryString=""+counter;
                            objectMap.put(temporaryString,map);
                        }catch (ParseException ex) {
                            Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println("Test# "+i);
                    for(String str: objects){
                        System.out.println(str);
                        for(Entry<String,Map<String,String>> printMap: objectMap.entrySet()){
                        Map<String,String> values=printMap.getValue();
                        col="id";
                        row=printMap.getKey();
                        for(Entry<String,String> printValues : values.entrySet()){
                            
                            col=col+" "+printValues.getKey();
                            row=row+printValues.getValue();
                            
                        }
                        if(b){
                            System.out.println(col);
                            System.out.println(row);
                            b=false;
                        }
                        else{
                            System.out.println(row);
                        }
                    }
                            
                    }
                }
	}
}
