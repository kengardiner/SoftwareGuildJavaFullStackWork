/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.practiceprogramming2;

/**
 *
 * @author n0252282
 */
public class TheOrderOfThings {
        public static void main(String[] args) {

        double number;
        String opinion, size, age, shape, color, origin, material, purpose;
        String noun;

        number = 5.0;
        opinion = "AWESOME";
        size = "teensy-weensy";
        age = "new";
        shape = "oblong";
        color = "BRIGHT yellow";
        origin = "AlphaCentaurian";
        material = "platinum";
        purpose = "good";

        noun = "dragons";

        // Using the + with strings, doesn't add it concatenates! (sticks them together)
        System.out.println(number + opinion + size + age + shape + color
                + origin + material + purpose + noun);
        System.out.println(number + " " + opinion + " " + size + " " + age + " " + shape + " " + color
                + " " + origin + " " + material + " " + purpose + " " + noun);
        System.out.println(opinion + " " + size + " " + number + " " + shape + " " + color + " " + age
                + " " + purpose + " " + noun + " " + origin + " " + material);
    }
}
