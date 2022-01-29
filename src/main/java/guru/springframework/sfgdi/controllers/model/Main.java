package guru.springframework.sfgdi.controllers.model;

import java.util.*;

public class Main {

    static class TimeMe{
        int ss;
        int mm;
        int hh;

        public TimeMe(int hh, int mm, int ss) {
            this.ss = ss;
            this.mm = mm;
            this.hh = hh;
        }

        public TimeMe() {
        }

        @Override
        public String toString() {
            return hh+":"+mm+":"+ ss;

        }
    }
    public static TimeMe getDiff(TimeMe t1 , TimeMe t2){
      //  System.out.println(t1 + "---" + t2);
        TimeMe result =  new TimeMe(0,0,0);
        if(t2.ss < t1.ss){
            t2.mm--;
            result.ss = t2.ss + 60 - t1.ss;
        }
        else{
            result.ss = t2.ss - t1.ss;
        }
        if(t2.mm < t1.mm){
            t2.hh--;
            result.mm = t2.mm + 60 - t1.mm;
        }
        else{
            result.mm = t2.mm - t1.mm;
        }

        result.hh = t2.hh - t1.hh;
       // System.out.println("diff "+result.toString());
        return result;
    }
    public static TimeMe addTime(TimeMe t1 , TimeMe t2){
        return new TimeMe(t2.hh + t1.hh, t2.mm + t1.mm, t2.ss + t1.ss);
    }
    public static void printTime(TimeMe t ){
        String s,h,m;
        s = Integer.toString(t.ss);
        m = Integer.toString(t.mm);
        h = Integer.toString(t.hh);

        if(s.length() < 2){
            s = "0" + s;
        }
        if(m.length() < 2){
            m = "0" + m;
        }
        if(h.length() < 2){
            h = "0" + h;
        }

        System.out.println(h + ":" + m +":" + s);
    }
    public static TimeMe convertTime(TimeMe t1){

        if(t1.ss > 59){
            t1.mm = t1.mm + t1.ss/60;
            t1.ss = t1.ss % 60;
        }
        if(t1.mm > 59){
            t1.hh = t1.hh + t1.mm/60;
            t1.mm = t1.mm % 60;
        }
        return t1;
    }

    public static boolean compareTime(TimeMe t1 , TimeMe t2){

       // System.out.println(t1.toString() +"/////" + t2.toString());
        if(t2.hh < t1.hh){
            return true;
        }
        else if(t2.hh == t1.hh && t2.mm < t1.mm){
            return true;
        }
        else if(t2.hh == t1.hh && t2.mm == t1.mm && t2.ss < t1.ss){
           return true;
        }

        return false;
    }

    static class Pair<U,V>{

        public  String val1;
        public  String val2;

        public Pair(String val1, String val2) {
            this.val1 = val1;
            this.val2 = val2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair<?, ?> pair = (Pair<?, ?>) o;

            if (val1 != null ? !val1.equals(pair.val1) : pair.val1 != null) return false;
            return val2 != null ? val2.equals(pair.val2) : pair.val2 == null;
        }

        @Override
        public int hashCode() {
            int result = val1 != null ? val1.hashCode() : 0;
            result = 31 * result + (val2 != null ? val2.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "val1='" + val1 + '\'' +
                    ", val2='" + val2 + '\'' +
                    '}';
        }
    }
    public static TimeMe getTime( String  p ){

        String str = p;
        String[] arrOfStr = str.split(":", 5);

        List<String> temp  = new ArrayList<>();
        for (String a : arrOfStr) {
            temp.add(a);
            //System.out.println(a);
        }
        Collections.reverse(temp);

        int k=0;
        int ss=0,hh=0,mm=0;
        for (String m: temp) {

            if(k==0){
                ss = Integer.parseInt(m);
            }
            else if(k==1){
                mm = Integer.parseInt(m);
            }
            else if(k==2){
                hh = Integer.parseInt(m);
            }
           k++;
        }
        //System.out.println("get time return" + hh + " " + mm +" " + ss );
        TimeMe t =  new TimeMe(hh,mm,ss);
        return t;
    }

/*
01:23:24
4
00:12:10 00:13:00
00:15:00 00:35:00
00:00:00 00:12:00
01:00:00 01:23:24


00:59:59
3
00:16:10 00:30:00
00:00:00 00:19:21
00:16:10 00:59:59

00:59:59
3
00:00:00 00:19:21
00:16:10 00:30:30
00:16:10 00:30:00
*
* */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        String sss =  sc.next();
        n = sc.nextInt();

        List<Pair<String,String>> list = new ArrayList<Pair<String,String>>();

        for (int i = 0; i < n; i++) {
            String str1 = sc.next();
            String str2 = sc.next();
            Pair<String,String> p = new Pair<String,String>(str1,str2);
            list.add(p);

        }

        //Bubble-Sort
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                Pair p1 = list.get(j);
                Pair p2 = list.get(j+1);

                TimeMe t1 = getTime(p1.val1);
                TimeMe t2 = getTime(p2.val1);

               // System.out.println(t1.toString());
              //  System.out.println(t2.toString());
                if(t1.hh == t2.hh){
                   if(t1.mm == t2.mm){
                       if(t1.ss == t2.ss){

                       }
                       else if(t1.ss > t2.ss){
                           list.set(j+1,p1);
                           list.set(j,p2);
                       }
                   }
                   else if(t1.mm > t2.mm){
                       list.set(j+1,p1);
                       list.set(j,p2);
                   }
                }
                else if (t1.hh > t2.hh){
                    list.set(j+1,p1);
                    list.set(j,p2);
                }

            }
        }

//        for (int i = 0; i < n; i++) {
//           System.out.println( list.get(i).toString());
//        }


        TimeMe oldtime = new TimeMe(0,0,0);
        TimeMe result =  new TimeMe(0,0,0);
        for (int i = 0; i < n; i++) {
            Pair p1 = list.get(i);
           // System.out.println(p1.val1);
            TimeMe t1 = getTime(p1.val1);
            TimeMe t2 = getTime(p1.val2);
            if(compareTime(t1,t2)){
                continue;
            }
            if(compareTime(t1,getTime(sss))){
                continue;
            }
            if(i==0){
                result = getDiff(t1,t2);
            }
            else{
                if(compareTime(oldtime , t1)){
                    if(compareTime(t2,oldtime))
                    result = addTime(result,getDiff(oldtime,t2));
                    else
                        continue;

                }
                else {
                    result = addTime(result, getDiff(t1, t2));
                }
            }
            oldtime = getTime(p1.val2);
           // System.out.println(result.toString());
        }

        TimeMe r = convertTime(result);


        printTime(r);

    }
}
