package edu.eci.arsw.blueprints.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.eci.arsw.blueprints.CommandExecutor;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author LauraRamosB
 */
@RestController
@RequestMapping( "/commands" )
public class commands {
    public HashMap<String,String > process=new HashMap<>();

    /**
     *
     * @param commad comando digitado por el usuario
     * @param ip identificador de usuario
     * @return Output generada al ejecutar el comando
     */
    @RequestMapping( value = "/{command}/{user}", method = RequestMethod.GET )
    public String command(@PathVariable("command") String commad , @PathVariable("user") String ip)  {
        //Ejecutamos el comando digitado por el usuario
        String outputString = CommandExecutor.run(commad,process.get(ip));
        //Si la salida del comando digitado por el usuario genera outputError no se agrega el comando a la pila de comandos
        if(!outputString.contains("<interactive>:")){
            String comandnew=process.get(ip)+commad+"\n";
            process.put((String)ip,(String)comandnew);
        }
        return outputString;
    }

    /**
     *
     * @param ip ip identificador de usuario
     * @return
     */
    @RequestMapping( value = "initialConsole/{user}", method = RequestMethod.GET )
    public boolean command2( @PathVariable("user") String ip){
        //Se inicializa una nueva pila de comandos para el usuario correspondiente
        process.put(ip,"");
        return true;
    }

}
