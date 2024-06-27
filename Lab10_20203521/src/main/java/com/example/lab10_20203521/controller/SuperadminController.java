package com.example.lab10_20203521.controller;

import com.example.lab10_20203521.entity.Imagen;
import com.example.lab10_20203521.repository.ImagenRepository;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("")
public class SuperadminController {

    final ImagenRepository imagenRepository;


    public SuperadminController(ImagenRepository imagenRepository
                                ) {

        this.imagenRepository = imagenRepository;

    }
    @GetMapping("")
    public String Plantilla() {

        return "Plantilla";
    }

    @PostMapping("/Guardar_Medicamento")
    public String guardarNuevoMedicamento(@RequestParam("foto1") Part foto1,
                                          Model model) {

        Optional<Imagen> optImagen = imagenRepository.findById(1);

        Imagen imagen = optImagen.get();
        try {
            InputStream fotoStream=foto1.getInputStream();
            byte[] fotoBytes=fotoStream.readAllBytes();
            imagen.setImage(fotoBytes);
            imagenRepository.save(imagen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] fotoBytes1 = imagen.getImage();
        String fotoBase64 = Base64.getEncoder().encodeToString(fotoBytes1);

        model.addAttribute("fotoBase64", fotoBase64);

        return "Plantilla";

    }


}
