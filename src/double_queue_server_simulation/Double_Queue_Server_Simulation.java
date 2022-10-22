/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package double_queue_server_simulation;
import java.util.*;
/**
 *
 * @author Omar Mohamed Adel Elsabaawy  ### 20191566958
 *
 **/

public class Double_Queue_Server_Simulation {  
    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int IAT = 0;
        int able_st = 0;
        int baker_st = 0;
        int baker_start = 0;
        int able_start = 0;
        int baker_end = 0;
        int able_end = 0;
        int clock = 0;
        int customer = 0;
        System.out.println("How many minutes you want to run this program?");
        int max_time = s.nextInt();
        int time_spent = 0;
        int [] able_idle = new int[100];
        int [] baker_idle = new int[100];
        int [] waiting = new int[100];
        
        while(clock <= max_time){
            
            if (customer == 0) {
                IAT = 0;
                baker_start = 0;
                baker_st = baker_ST(generate_ST());
                baker_end = baker_st + baker_start;
                time_spent = baker_end - baker_start;
                System.out.println("Customer "+customer+" | IAT " + IAT + " | clock " + clock + " | baker start "
                        + baker_start + " | Service time "+ baker_st + " | baker end " + baker_end + " | time spend in the system " 
                        + time_spent + " | baker idle " + baker_idle[customer] + " | waiting " + waiting[customer]);
            }else{
                IAT = calculate_arrival_time(generate_IAT());
                clock += IAT;
                if(clock > max_time){
                    break;
                }
                if (clock >= baker_end){
                    if (baker_end < clock){
                        baker_idle[customer] = (clock - baker_end);
                        baker_start = clock;
                    }   
                    else{
                        baker_start = baker_end;
                    }
                        baker_st = baker_ST(generate_ST());
                        baker_end = baker_start + baker_st;
                        time_spent = baker_end - baker_start;
                        System.out.println("Customer "+customer+" | IAT " + IAT + " | clock " + clock + " | baker start "
                        + baker_start + " | Service time "+ baker_st + " | baker end " + baker_end + " | time spend in the system " 
                        + time_spent + " | baker idle " + baker_idle[customer] + " | waiting " + waiting[customer]);

                }else if(clock >= able_end){
                    if (able_end < clock){
                        able_idle[customer] = (clock - able_end);
                        able_start = clock;
                    }   
                    else{
                        able_start = able_end;
                    }
                        able_st = able_ST(generate_ST());
                        able_end = able_start + able_st;
                        time_spent = able_end - able_start;
                        System.out.println("Customer "+customer+" | IAT " + IAT + " | clock " + clock + " | able start "
                        + able_start + " | Service time "+ able_st + " | able end " + able_end + " | time spend in the system " 
                        + time_spent + " | able idle " + able_idle[customer] + " | waiting " + waiting[customer]);
                }else if(baker_end <= able_end){
                    if (baker_end < clock) {
                        baker_idle[customer] = (clock - baker_end);
                        baker_start = clock;
                    }else if (baker_end > clock) {
                        waiting[customer] = (baker_end - clock);
                        baker_start = baker_end;
                    }
                    baker_st = baker_ST(generate_ST());
                    baker_end = baker_start + baker_st;
                    time_spent = baker_end - baker_start;
                    System.out.println("Customer "+customer+" | IAT " + IAT + " | clock " + clock + " | baker start "
                    + baker_start + " | Service time "+ baker_st + " | baker end " + baker_end + " | time spend in the system " 
                    + time_spent + " | baker idle " + baker_idle[customer] + " | waiting " + waiting[customer]);
                }else if(able_end < clock || able_end > clock){
                    if (able_end < clock) {
                        able_idle[customer] = (clock - able_end);
                        able_start = clock;
                    }else if (able_end > clock) {
                        waiting[customer] = (able_end - clock);
                        able_start = able_end;
                    }
                    able_st = able_ST(generate_ST());
                    able_end = able_start + able_st;
                    time_spent = able_end - able_start;
                    System.out.println("Customer "+customer+" | IAT " + IAT + " | clock " + clock + " | able start "
                    + able_start + " | Service time "+ able_st + " | able end " + able_end + " | time spend in the system " 
                    + time_spent + " | able idle " + able_idle[customer] + " | waiting " + waiting[customer]);
                }
            }
            
        customer++;
        }
        System.out.println("");
        int total_waiting_time = 0;
        int waits = 0;
        for (int w : waiting) {
            if (w != 0) {
                waits++;
                total_waiting_time+=w;
            }
        }        
        System.out.println("the average waiting time: " + (double) total_waiting_time / customer);
        System.out.println("the probability of waiting time: " +(double) waits*100 / customer + "%");
                
    }
    
    public static int able_ST(int st){
        if(st >= 0 && st <= 29){
            return 2;
        }else if(st >= 30 && st <= 57){
            return 3;
        }else if (st >= 58 && st <= 82) {
            return 4;
        }else{
            return 5;
        }
    }

    public static int baker_ST(int st){
        if(st >= 0 && st <= 34){
            return 3;
        }else if(st >= 35 && st <= 59){
            return 4;
        }else if (st >= 60 && st <= 79) {
            return 5;
        }else{
            return 6;
        }
    }
    
    
    public static int generate_ST(){
        int st = generate_random_number();
        return st;
    }
    
    public static int calculate_arrival_time(int IAT){
        if(IAT >= 0 && IAT <= 24){
            return 1;
        }else if(IAT >= 25 && IAT <= 64){
            return 2;
        }else if (IAT >= 65 && IAT <= 84) {
            return 3;
        }else{
            return 4;
        }
    }
    
    public static int generate_IAT(){
        int IAT = generate_random_number();
        return IAT;
    }
    
    public static int generate_random_number(){
        Random random_number = new Random();
        return random_number.nextInt(100);
    }  
    
}
