package com.bank.view.command;

import com.bank.ZimaRed;

public class WelcomePage {

    public void Key() {
        LoginPage customerLogin = new LoginPage();
        RigesterPage rigesterCustomer = new RigesterPage();

        System.out.println("Hi, and welcome to our bank press one of the next numbers to continue ..." + 
            " \n 1 : loging \n 2 : rigester \n 3 : exit");

        while(true){
            System.out.print("key: ");
            int key = ZimaRed.scanner.nextInt();
            ZimaRed.scanner.nextLine();
            
            if(key <= 3 && key >= 1){
                switch(key){
                    case 1:
                        customerLogin.login();
                        break;
                        
                    case 2:
                        rigesterCustomer.Add();
                        break;
                        
                    case 3:
                        System.out.println("goodbye babe");
                        System.exit(0);
                        break;                   
                    }
                }else{
                System.out.println(" please enter your choise correctly");
            }
        }
    }
}

