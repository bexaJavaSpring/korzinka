package uz.korzinka;

import uz.korzinka.templates.FrontEndAction;


public class Main {

    public static void main(String[] args) {
        System.out.println("\n\n*************** Project: Computer Equipments Warehouse *****************");
        System.out.println("--------------- Author: Xanifa  ---------------");
        System.out.println("--------------- xanifa@student.itpu.uz ---------------");
        System.out.println("--------------- Creation date: since 01/01/24 16:35 ---------------");
        System.out.println("--------------- Version: version-0.0.1 JDK 17 ---------------");
        FrontEndAction frontEndAction = new FrontEndAction();
        frontEndAction.frontTemplate();
    }
}