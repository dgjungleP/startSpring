package com.jungle.tacos.controller;

import com.jungle.tacos.entity.Ingredient;
import com.jungle.tacos.entity.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        ingredientList.add(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        ingredientList.add(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        ingredientList.add(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        ingredientList.add(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        ingredientList.add(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
        ingredientList.add(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientList.add(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        ingredientList.add(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        ingredientList.add(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        for (Ingredient.Type type : Ingredient.Type.values()) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredientList, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {
        if (errors.hasErrors()) {
            return "redirect:/design";
        }
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredientList, Ingredient.Type type) {
        return ingredientList.stream()
                .filter(data -> data.getType().equals(type))
                .collect(Collectors.toList());
    }

}
