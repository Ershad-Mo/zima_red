package com.bank.view.command;

import com.bank.ZimaRed;

public class WelcomePage {

    public void Key() {
        LoginPage customerLogin = new LoginPage();
        RigesterPage rigesterCustomer = new RigesterPage();

        int key10;
        do{
            
            System.out.println("\n\n\nHi, and welcome to our bank press one of the next numbers to continue ..." + 
            " \n 1 : loging \n 2 : rigester \n 3 : exit");

            System.out.print("key: ");
            int key = ZimaRed.scanner.nextInt();
            ZimaRed.scanner.nextLine();
            key10 = key;
            
            if(key <= 3 && key >= 1){
                switch(key){
                    case 1:
                    // System.out.println("if you wanna go back press 0 or 1 to continue ");
                    // int key1 = ZimaRed.scanner.nextInt();
                    // ZimaRed.scanner.nextLine();
                    // if(key1 == 0){
                    //     break;
                    // }
                        customerLogin.login();
                    break;

                    case 2:
                    // System.out.println("if you wanna go back press 0 or 1 to continue ");
                    // int key2 = ZimaRed.scanner.nextInt();
                    // ZimaRed.scanner.nextLine();
                    // if(key2 == 0){
                    //     break;
                    // }
                        rigesterCustomer.Add();
                        break;
                        
                    case 3:
                        System.out.println();
                        System.out.println("goodbye babe");
                        System.exit(0);
                        break;                   
                    }
                }else{
                System.out.println(" please enter your choise correctly");
            }
        }while(key10 != 3);
    }
}

