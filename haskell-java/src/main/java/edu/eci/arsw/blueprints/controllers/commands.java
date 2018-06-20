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
    private HashMap<String,Process> process= new HashMap<>();
    // the threads are kept track of with a linked list
    private static LinkedList<Thread> list = new LinkedList<Thread>();
    @RequestMapping( value = "/{command}/{user}", method = RequestMethod.GET )
    public String command(@PathVariable("command") String commad , @PathVariable("user") String ip)  {
        // open a new PrintWriter and BufferedReader on the socket
        System.out.print(commad);
        String outString = CommandExecutor.run(commad,process.get(ip));
        System.out.print(outString);
        return outString;
    }

    @RequestMapping( value = "/init/{user}", method = RequestMethod.POST )
    public void command2( @PathVariable("user") String ip) throws IOException {
        // open a new PrintWriter and BufferedReader on the socket
        process.put(ip,Runtime.getRuntime().exec("ghci"));
        BufferedWriter w = new BufferedWriter (new OutputStreamWriter(process.get(ip).getOutputStream()));
        w.write("let x=5");
        w.close();

    }

}
