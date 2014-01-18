package cache;

import java.io.*;
import java.util.*;
import java.util.regex.*;



public class Cache {
static List<Contact> contacts = new ArrayList<Contact>();
static ArrayList retreiveuser()
                { ArrayList<String> ad=new ArrayList<String>();
    Collections.sort(contacts, Contact.COMPARE_BY_SRC);
    //System.out.println(contacts.get(0).srcid.toString());
    for(int i=0;i<contacts.size();i++)
    {   if(i+1>=contacts.size()){

        ad.add(contacts.get(i).srcid.toString());
            break;}
        if(contacts.get(i).srcid.toString().compareTo(contacts.get(i+1).srcid.toString())==0)
                 {
            continue;
        }
 else
        {
     ad.add(contacts.get(i).srcid.toString());
    }}return(ad);
}
static ArrayList retreivedocs()
    {ArrayList<String> ad=new ArrayList<String>();
    Collections.sort(contacts, Contact.COMPARE_BY_DOC);
            {
        for(int i=0;i<contacts.size();i++)
    {   if(i+1>=contacts.size()){
            ad.add(contacts.get(i).docid.toString());
            break;}

        if(contacts.get(i).docid.toString().compareTo(contacts.get(i+1).docid.toString())==0)
                 {
            continue;
        }
 else
        {
     ad.add(contacts.get(i).docid.toString());
    }}
    }return(ad);
}

static ArrayList getPage(String srcid)
    {ArrayList<String> ad=new ArrayList<String>();
    int index=-1;int cunt=0;ad.add("User id  "+srcid+" has accessed the documents with following docid and the correponding Page numbers");String temp="";
    for(int i=0;i<contacts.size();i++)
    {
        if(contacts.get(i).srcid.toString().compareTo(srcid)==0)
        {
            String url=contacts.get(i).url.toString();
            if(!contacts.get(i).docid.toString().equalsIgnoreCase("0")){
             if(temp.compareTo(contacts.get(i).docid.toString())==0)
             {

             }else{
            ad.add("Viewed document= " + contacts.get(i).docid.toString());
            ad.add("Pagenumbers");
            temp=contacts.get(i).docid.toString();}
            if(url.contains("pagenumber"))
            {
//System.out.println("hi");
                index = url.indexOf("pagenumber");
                ad.add(contacts.get(i).url.substring(index+11, index+12)+" , ");


            }}
        if(url.contains("hash"))
        {
            cunt++;
            index=url.indexOf("hash");
            ad.add("Downloaded document hash is "+url.substring(index+5,index+37));



        }   //System.out.println();

        }

      //  ad.add("/n");
    }ad.add("No of Downloaded documents ="+cunt);
    return(ad);
}
static ArrayList getUsersfordoc(String docid)
    {ArrayList<String> ad=new ArrayList<String>();
    int index=-1;ad.add("Doc id :"+docid);String temp="";
    for(int i=0;i<contacts.size();i++)
    {
        if(contacts.get(i).docid.toString().compareTo(docid)==0)
        {
            String url=contacts.get(i).url.toString();
            if(!contacts.get(i).srcid.toString().equalsIgnoreCase("0")){
             if(temp.compareTo(contacts.get(i).srcid.toString())==0)
             {

             }else{
            ad.add("src id =" + contacts.get(i).srcid.toString());
            ad.add("Pagenumbers");
            temp=contacts.get(i).srcid.toString();}
            if(url.contains("pagenumber"))
            {
//System.out.println("hi");
                index = url.indexOf("pagenumber");
                ad.add(contacts.get(i).url.substring(index+11, index+12)+" , ");


            }}
            //System.out.println();



        }
        }return(ad);}
     public static void main(String[] args) {
      //  ma();
     }
     static void ma(){
       try{

   FileInputStream fstream = new FileInputStream("C:\\Users\\user\\Documents\\NetBeansProjects\\Mozilla\\src\\mozilla\\_CACHE_003_");
        //   FileInputStream fstream = new FileInputStream("C:/Users/Administrator/AppData/Local/Google/Chrome/User Data/Default/Cache/data_1");
          //  FileInputStream fstream = new FileInputStream("C:/Users/Administrator/Desktop/_CACHE_003_");
   DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  PrintWriter out = new PrintWriter(new FileWriter("output1.txt"));

  //BufferedWriter out = new BufferedWriter(new FileWriter("C:/Users/Administrator/Desktop/output1.txt"));
  String strLine;

  while ((strLine = br.readLine()) != null)   {
        List<String> result = new ArrayList<String>();


         String urlPattern ="((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";

    Pattern p = Pattern.compile(urlPattern,Pattern.CASE_INSENSITIVE);

    Matcher m = p.matcher(strLine);

    while (m.find()) {
String did="0";String sid="0";
    String url= strLine.substring(m.start(0),m.end(0));
   // if(!(url.contains("https://docs.google.com/comments"))){

if(url.contains("https://docs.google"))
{
    if(url.contains("docs.google.com/comments")) {

        continue;
    }
    if(url.contains("srcid"))

    {   int index=url.indexOf("srcid");


        sid= url.substring(index+5+1, index+12+5+1);
        }
    if((!url.contains("srcid"))&&(url.contains("id")))

        {
        int index=url.indexOf("id");


        sid= url.substring(index+3, index+12+3);
    }
    if(url.contains("hash"))
    {int index=url.indexOf("docs");
        sid=url.substring(index+238,index+250);
    }
 /*else if(url.contains("authuser"))
    {

 }*/
    if(url.contains("docid"))

    {   int index=url.indexOf("docid");


        did= url.substring(index+6, index+6+32);
        }
        contacts.add(new Contact(strLine.substring(m.start(0),m.end(0)),sid,did));
        //out.println(result);

         }}


  }
Collections.sort(contacts, Contact.COMPARE_BY_SRC);
       for(int i=0;i<contacts.size();i++)

       {out.println(contacts.get(i).url.toString());
          out.println("SRCID :"+contacts.get(i).srcid.toString());

        // System.out.println("Contenttype:"+contacts.get(i).srcid.toString());
           out.println("DOCID :"+contacts.get(i).docid.toString());

          // String url_temp =contacts.get(i).url.toString();


       }out.close();

  in.close();
       //retreiveuser();
       //retreivedocs();
       //getPage("1QgnAyCgLsEy");
       //getUsersfordoc("379464894c4e4c507dc3145dc06a7394");
       }
  catch (Exception e){
  System.err.println("Error: " + e.getMessage());
  }

    }

}



class Contact{
    Contact(String name,String c,String c1)
    {
        this.url=name;
       // this.date=phone;
       this.srcid=c;
        this.docid=c1;
    }
//public Date date;
    public String url;
  public String srcid;
    public String docid;
 public static Comparator<Contact> COMPARE_BY_SRC = new Comparator<Contact>() {
        public int compare(Contact one, Contact other) {
            return one.srcid.compareTo(other.srcid);
        }
 };
        public static Comparator<Contact> COMPARE_BY_DOC = new Comparator<Contact>() {
        public int compare(Contact one, Contact other) {
            return one.docid.compareTo(other.docid);
        }
 };
}