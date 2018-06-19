package edu.eci.arsw.blueprints.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.eci.arsw.blueprints.CommandExecutor;
import org.springframework.web.bind.annotation.*;

import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    // the threads are kept track of with a linked list
    private static LinkedList<Thread> list = new LinkedList<Thread>();
    @RequestMapping( value = "/{command}/{user}", method = RequestMethod.POST )
    public String command(@PathVariable("command") String commad , @PathVariable String ip)  {
        // open a new PrintWriter and BufferedReader on the socket
        String outString = CommandExecutor.run(commad);

        return outString;
    }

}
