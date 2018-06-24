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
    private static String hostName ="18.236.157.85";
    // create a variable to initialize new threads with
    private static Thread thrd = null;
    public static String output = null;
    public HashMap<String,String > process=new HashMap<>();

    @RequestMapping( value = "/{command}/{user}", method = RequestMethod.GET )
    public String command(@PathVariable("command") String commad , @PathVariable("user") String ip)  {
        // open a new PrintWriter and BufferedReader on the socket

        String outString = CommandExecutor.run(commad,process.get(ip));

        if(!outString.contains("<interactive>:")){
            String comandnew=process.get(ip)+commad+"\n";
            process.put((String)ip,(String)comandnew);
        }
        return outString;
    }

    @RequestMapping( value = "initialConsole/{user}", method = RequestMethod.GET )
    public boolean command2( @PathVariable("user") String ip) throws IOException, InterruptedException {
        process.put(ip,"");
        return true;
    }

}
