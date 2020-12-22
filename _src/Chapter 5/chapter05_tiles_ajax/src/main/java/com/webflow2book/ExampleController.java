package com.webflow2book;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController {
    // Create a pseudorandom number generator
    private static Random randomizer = new Random();

    @RequestMapping("/getNumbers.htm")
    public ModelMap getRandomNumbers() {
        List<Integer> randomNumberList = new ArrayList<Integer>();
        // Create 10 new random numbers
        for (int i = 0; i < 10; ++i) {
            randomNumberList.add(randomizer.nextInt());
        }
        // Return the numbers as modelMap
        return new ModelMap("numberList", randomNumberList);
    }
}
