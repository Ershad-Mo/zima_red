package com.bank;
import java.util.Scanner;

import com.bank.view.command.WelcomePage;


public class ZimaRed {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        WelcomePage menu = new WelcomePage();
        menu.Key();
    }
}