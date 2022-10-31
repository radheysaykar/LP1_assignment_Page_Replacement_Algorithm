import java.util.*;
// import java.io.*;
public class Page_replacement_algo {
    static int[] pages1=  {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
    static int[] pages2={7, 0, 1, 2, 0, 3, 0, 4, 20, 3, 0, 3, 2};
    static int[] pages3={7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
   static int capacity=4;
   static int page_frames[]=new int[capacity];

    
    public static void  fifo()
    {
        for (int i = 0; i < capacity; i++) page_frames[i]=-1;

        int page_faults=0;
        int page_frames_ptr=0;
            page_frames[page_frames_ptr++]=pages1[0];
            page_faults++;
           for (int i = 1; i < pages1.length; i++) {
                if(page_frames_ptr==capacity){
                    page_frames_ptr=0;
               }
               
               boolean found=false;
               for (int j = 0; j < capacity; j++) {
                    if(page_frames[j]==pages1[i])
                    {
                        found=true;
                        break;
                    }
               }
               if(found) continue;
                page_frames[page_frames_ptr++]= pages1[i];
               page_faults++;
               
           }
           System.out.println("fifo page faults:");

           System.out.println(page_faults);
        //    System.out.println("*******");

                                                                                                                                                                                                                                                                                                                                                       
    }
   
    public static void optimal()
    {
        for (int i = 0; i < capacity; i++) page_frames[i]=-1;

        int page_faults=0;
        int storage=0,ptr=0;

        while(storage<capacity)
        {
           
                Boolean found=false;
            for (int i = 0; i < storage; i++) {
                if(pages2[ptr]==page_frames[i])
                {
                    found=true;
                    break;
                }
            }
            if(found){
                ptr++;
                continue;
            } 
            page_frames[storage]= pages2[ptr++];
            if(ptr==pages2.length) return;
            storage+=1;
           page_faults++;
        }
        for (int i = ptr; i < pages2.length; i++) {
           
            Boolean found=false;
            for (int j = 0; j < capacity; j++) {
                if(pages2[i]==page_frames[j])
                {
                    found=true;
                    break;
                }
            }
            if(found) continue;
            int page_frame=-1, farthest=i+1;
            for (int j = 0; j < capacity; j++) {
                int j2;
                for (j2 = i+1; j2 < pages2.length; j2++) {
                    if(page_frames[j]==pages2[j2])
                    {
                        break;
                    }
                }
                if(j2>farthest){
                    farthest=j2;
                    page_frame=j;
                    if(j2==pages2.length) break;
                }
            }
            
            page_frames[page_frame]=pages2[i];
            page_faults++;
        }
        System.out.println("optimal page faults:");

        System.out.println(page_faults);   
    }
    

    public static void lru()
    { 
        for (int i = 0; i < capacity; i++) page_frames[i]=-1;

        int page_faults=0;
        int storage=0,ptr=0;

        while(storage<capacity)
        {
           
                Boolean found=false;
            for (int i = 0; i < storage; i++) {
                if(pages2[ptr]==page_frames[i])
                {
                    found=true;
                    break;
                }
            }
            if(found){
                ptr++;
                continue;
            } 
            page_frames[storage++]= pages2[ptr++];
            if(ptr==pages2.length) return;
            // System.out.println(pages2[ptr-1]);   
           page_faults++;
        }
        for (int i = ptr; i < pages2.length; i++) 
        {
           
            Boolean found=false;
            for (int j = 0; j < capacity; j++) 
            {
                if(pages2[i]==page_frames[j])
                {
                    found=true;
                    break;
                }
            }
            if(found) continue;
            int page_frame=-1, farthest=i-1;
            for (int j = 0; j < capacity; j++) 
            {
                int j2;
                for (j2 = i-1; j2 >=0; j2--)
                {
                    if(page_frames[j]==pages2[j2])
                    {
                        break;
                    }
                }
                if(j2<farthest)
                {
                    farthest=j2;
                    page_frame=j;
                    if(j2==-1) break;
                }
            }
            // System.out.println(pages2[i]);
            // System.out.println("^");

            // System.out.println(page_frame);
            page_frames[page_frame]=pages2[i];
            // for (int j = 0; j < capacity; j++) System.out.print(page_frames[j]);

            page_faults++;
        }
        System.out.println("lru page faults:");

        System.out.println(page_faults);   
    

    }

    

    public static void main(String[] args) {

        fifo();
        optimal();    
        lru();
      }
    }

