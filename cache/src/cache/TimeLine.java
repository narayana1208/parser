package cache;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TimeLine {
    static FileInputStream fstream1;
    static StringBuffer s=new StringBuffer("");
    static ArrayList<String> startVal=new ArrayList<String>();
    static ArrayList<String> endVal=new ArrayList<String>();
    static ArrayList<String> srcID=new ArrayList<String>();
    static ArrayList<String> docID=new ArrayList<String>();
    static ArrayList<String> status=new ArrayList<String>();

    int counter=0;
    int total=0;
    String sid="";
    static int pointer=0;
    static int pointer1=0;
    static int i=0;
    static String temp2="";
    static String did="";

    public static void main(String args[])
    {
        createTimeLine();


    }
    public static ArrayList createTimeLine()
    {ArrayList<String> ad=new ArrayList<String>();
         try{
        
        String strLine1;
        fstream1 = new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Mozilla\\src\\mozilla\\_CACHE_003_");
        DataInputStream in = new DataInputStream(fstream1);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        while ((strLine1 = br.readLine()) != null)
        {
                    s=s.append(strLine1);
                }
                //print URLs
               // System.out.println(new Parser().extractUrls(s.toString()));
                TimeLine tl=new TimeLine();
                tl.extractUrls(s.toString());
                //while(startVal!=null)
                    while(i<startVal.size())
                {
                String marker=startVal.get(i).replaceFirst(".*start","").toString();
                //String marker=startVal.get(i);
                //System.out.println(marker);
                pointer=Integer.valueOf(marker).intValue();
                //System.out.println(s.charAt(pointer-73));
                String temp=s.substring(pointer-83,pointer-48);
                if(temp.contains("Date")==true)
                {
                ad.add("Source ID :"+srcID.get(i));
                if(i<=docID.size()){
                ad.add("Document ID :"+docID.get(i));}
                else
                {
                ad.add("Document ID :"+"Not available");
                }
                ad.add(temp+"\n");

                }
                status.add(temp);
                i++;
                //System.out.println("hello"+i);
                }
                    //System.out.println("break");
                 //for(String val1:status)
                    for(int j=0;j<status.size();j++)
                 {
                     //if(val1.contains("Date")==false)
                     if(status.get(j).contains("Date")==false)
                     {
                         //System.out.println("nkn");
                         String marker1=startVal.get(j).replaceFirst(".*start","").toString();
                         pointer1=Integer.valueOf(marker1).intValue();
                         //String temp1=s.substring(pointer1+312,pointer1+347);
                         String temp1=s.substring(pointer1,pointer1+400);
                         //System.out.println(temp1);
                         if(temp1.contains("Date:"))
                         {
                             int index=temp1.indexOf("Date:");
                             temp2=temp1.substring(index,index+35);
                             ad.add("Source ID :"+srcID.get(j));
                             if(j<docID.size()){
                ad.add("Document ID :"+docID.get(j));}
                else
                {
                ad.add("Document ID :"+"Not available");
                }
                         ad.add(temp2+"\n");
                             /*
                              String mid=ruff.substring(p, p+36);
                 //print date
                 //System.out.println(mid);
                 String mid_date=ruff.substring(p+6, p+36).trim();

                 if(mid_date.contains("GMT"))
                 {
                 //print only those urls that have date
                 String url_date=s.substring(Integer.valueOf(begin1).intValue(),Integer.valueOf(end1).intValue());
                 url.add(url_date);
                              */
                         }

                         //System.out.println(srcID.get(j));
                         //System.out.println(temp2);
                     }
                 }

in.close();

             }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }
return(ad);


        }




 public  void extractUrls(String value)
    {
    //List<String> result = new ArrayList<String>();
    String urlPattern = "https://docs.google.com/[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";;
    Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(value);
    while (m.find()) {
        String url_temp=value.substring(m.start(0),m.end(0));
        String start_id=counter+"start"+m.start(0);
        String end_id=counter+"end"+m.end(0);

        String val=url_temp+ " "+m.start(0)+" "+m.end(0);



        if(val.contains("srcid"))
        {
            total++;
            int index=val.indexOf("srcid");
            sid= val.substring(index+5+1, index+13+5+1);
            srcID.add(sid);
            //System.out.println(sid);
            startVal.add(start_id);
            endVal.add(end_id);
            counter++;


        }

        if(val.contains("docid"))

    {   int index=val.indexOf("docid");


        did= val.substring(index+6, index+6+32);
        docID.add(did);
        }
             else
             {
               //System.out.println("Doc ID :"+did);
             }

        }

        //System.out.println(url_temp+ " "+m.start(0)+" "+m.end(0));
                       System.out.println("Total documents parsed :"+total+"\n");

    }

    }



