package edu.eci.arsw.blueprints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


/* 
 * Class containing methods for taking the command string (a string with a digit 1-6)
 * received from the server and converting that into its respective Linux shell command, 
 * then executing it and returning the results.
 */
public class CommandExecutor {


	public  CommandExecutor(){

	}

	/**
	 * Runs the shell command after first using parseCommand() to determine which
	 * command to run.
	 *
	 * @param commandString		A string containing a command;
	 * @param commands		A string containing commands;
	 * @return			A string containing the results of the shell command.
	 */
	public static  String run(String commandString,String commands) {
		String result = "";
		String line;
		boolean error=false;
		try {
			// start the shell command running as a child processes
            Process child= Runtime.getRuntime().exec("ghci");
			// open a BufferedWrite the input of the child process
			BufferedWriter w = new BufferedWriter (new OutputStreamWriter (child.getOutputStream()));
			// open a BufferedReader to read the output of the child process
			BufferedReader output = new BufferedReader(new InputStreamReader(child.getInputStream()));
			// open a BufferedReader to read the error output of the child process
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(child.getErrorStream()));
			if(commands!=""){
				String[] parts = commands.split("\n");
				for(int i=0;i<parts.length;i++){
					w.write(parts[i]+"\n");
				}
			}
			// Write input in child process
			w.write(commandString);
			w.close();
			// while the child process is still error, add the output to the result string
            while ((line = errorReader.readLine()) != null) {
                result = result.concat(line+"\n");
                error=true;
            }
            errorReader.close();
			// while the child process is still outputting, add the output to the result string
			while ((line = output.readLine()) != null && !error) {
				result = result.concat(line+"\n");
			}
			System.out.print(result);
			// will know that the server is done sending
			result = result.concat("END_MESSAGE");
			output.close();
            child.destroy();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
